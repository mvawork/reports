package ru.sovcombank.frontoffice.groovy

def getTemplateVariables() {

}

def doFillTemplate(reportTemplate) {
    f = reportTemplate.getVariableFieldByName("CliName");
    f.updateField("Корчемкин", null);
    f = reportTemplate.getVariableFieldByName("ChiefName");
    f.updateField("Кузнецов", null);
}

