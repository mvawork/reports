package ru.sovcombank.frontoffice;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sovcombank.frontoffice.entity.FrontSysReport;
import ru.sovcombank.frontoffice.repository.FrontSysReportRepository;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

    private FrontSysReportRepository repo;
    private Grid<FrontSysReport> grid;

    @Autowired
    public VaadinUI(FrontSysReportRepository repo) {
        this.repo = repo;
        this.grid = new Grid<>(FrontSysReport.class);
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.addComponentsAndExpand(grid);
        grid.setSizeFull();
        HorizontalLayout action = new HorizontalLayout();
        Button runButton = new Button("Запустить");
        action.addComponent(runButton);
        root.addComponent(action);
        setContent(root);
        refresh();
    }

    private void refresh() {
        grid.setItems(repo.findAll());
    }


}

