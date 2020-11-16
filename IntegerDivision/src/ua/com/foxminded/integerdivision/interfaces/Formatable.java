package ua.com.foxminded.integerdivision.interfaces;

import ua.com.foxminded.integerdivision.calculator.CalculationDto;

public interface Formatable {
    void createResultTmp(CalculationDto dto);
    void formatResult(CalculationDto dto);
    void addDividendLine(int i, CalculationDto dto);
    void addDividerLine(int i, CalculationDto dto);
    void addSeparator(int separatorLength, int spacesBefore);
    void addResultValueOfLongDivision(CalculationDto dto);
    void formatFirstLine(CalculationDto dto);
    void formatSecondLine(CalculationDto dto);
    void formatThirdLine(CalculationDto dto);
    void addSpacesBefore(StringBuilder line, int spacesBefore);
    void addSpacesAfter(StringBuilder line, int spacesAfter);
    String createResult(CalculationDto dto);

}
