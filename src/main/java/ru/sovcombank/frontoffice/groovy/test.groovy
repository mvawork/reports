package ru.sovcombank.frontoffice.groovy

f = reportTemplate.getVariableFieldByName("CliName");
f.updateField("Корчемкин",  null);
f = reportTemplate.getVariableFieldByName("ChiefName");
f.updateField("Кузнецов",  null);

