package ru.sovcombank.frontoffice;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.vaadin.annotations.Theme;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import org.odftoolkit.simple.TextDocument;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sovcombank.frontoffice.entity.FrontSysReport;
import ru.sovcombank.frontoffice.exceptions.EAppException;
import ru.sovcombank.frontoffice.repository.FrontSysReportRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI implements Button.ClickListener {

    private FrontSysReportRepository repo;
    private Grid<FrontSysReport> grid;
    private SingleSelectionModel<FrontSysReport> ssm;
    
    private GroovyScriptEngine groovyScriptEngine;
    private Path groovyScriptPath;
    @Autowired
    public VaadinUI(FrontSysReportRepository repo) {
        this.repo = repo;
        grid = new Grid<>(FrontSysReport.class);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        ssm = (SingleSelectionModel<FrontSysReport>) grid.getSelectionModel();
        createGroovyScriptEngine();
    }

    private void createGroovyScriptEngine() {
        try {
            groovyScriptPath = Files.createTempDirectory("frontoffice");
            groovyScriptPath.toFile().deleteOnExit();
            groovyScriptEngine = new GroovyScriptEngine(new java.net.URL[]{groovyScriptPath.toUri().toURL()});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.addComponentsAndExpand(grid);
        grid.setSizeFull();
        HorizontalLayout action = new HorizontalLayout();
        Button runButton = new Button("Запустить");
        runButton.addClickListener(this);
        action.addComponent(runButton);
        root.addComponent(action);
        setContent(root);
        refresh();
    }

    private void refresh() {
        grid.setItems(repo.findAll());
    }


    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        Optional<FrontSysReport> item = ssm.getFirstSelectedItem();
        if (item.isPresent()) {
            try {
                StreamResource streamResource = new StreamResource(new StreamResource.StreamSource() {

                    ByteOutputStream outputStream = new ByteOutputStream();
                    {
                        if (item.get().getRe_rpt_tpl().length > 0) {
                            ByteArrayInputStream inputStream = new ByteArrayInputStream(item.get().getRe_rpt_tpl());
                            if ("odt".equals(item.get().getRe_rpt_ext()) && item.get().getRe_rpt_scr() != null && !item.get().getRe_rpt_scr().isEmpty()) {
                                File sf = new File(groovyScriptPath.toFile(), item.get().getRe_rpt_prn() + ".groovy");
                                sf.deleteOnExit();
                                try (FileOutputStream fs = new FileOutputStream(sf)) {
                                    fs.write(item.get().getRe_rpt_scr().getBytes());
                                }
                                TextDocument report = TextDocument.loadDocument(inputStream);
                                Binding binding = new Binding();
                                groovyScriptEngine.createScript(sf.getName(), binding).invokeMethod("doFillTemplate", report);
                                //groovyScriptEngine.run(sf.getName(), binding);
                                report.save(outputStream);
                            } else {
                                outputStream.write(inputStream);
                            }
                        } else {
                            //outputStream.write(runReport.run(design, rptTyp, params));
                        }

                    }

                    @Override
                    public InputStream getStream() {
                        return outputStream.newInputStream();
                    }

                }, item.get().getRe_rpt_nam() + "." + item.get().getRe_rpt_ext());

                switch (item.get().getRe_rpt_ext()){
                    case "pdf":
                        streamResource.setMIMEType("application/pdf");
                        break;
                    case "odt":
                        streamResource.setMIMEType("application/vnd.oasis.opendocument.text");
                        break;
                    default:
                        throw new EAppException("Неизвестный тип отчета " + item.get().getRe_rpt_ext());
                }
                //noinspection deprecation
                UI.getCurrent().getPage().open(streamResource, "_blank", true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

