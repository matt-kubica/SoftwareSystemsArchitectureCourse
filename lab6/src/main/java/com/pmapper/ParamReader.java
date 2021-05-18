package com.pmapper;

import com.pmapper.annotations.IsParameterPresent;
import com.pmapper.annotations.Mandatory;
import com.pmapper.annotations.TextValue;
import com.pmapper.exceptions.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParamReader {

    private Object paramInstance = null;
    private ParamMapperContainer container = null;
    private List <String> args = null;
    private List <Method> methods = null;
    private Set <String> purifiedArgsSet = null;


    public Object getParamInstance() {
        return this.paramInstance;
    }


    public ParamReader(ParamMapperContainer container, String[] args) {
        this.container = container;
        this.args = Arrays.asList(args);
        this.methods = Arrays.asList(container.getParamClass().getMethods());
        this.purifiedArgsSet = this.args.stream()
                .map(arg -> arg.replaceFirst("-", ""))
                .collect(Collectors.toSet());
    }

    public void initializeParamInstance() throws NoDefaultConstructorException {
        try {
            this.paramInstance = this.container.getParamClass().getConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new NoDefaultConstructorException();
        }
    }

    public void checkForIsPresentFields() throws NoAssociatedSetterException {
        for (Field field : this.container.getPresentFields()) {

            Method setterMethod = this.methods.stream()
                    .filter(method -> method
                            .getName()
                            .matches(isParameterPresentSetterRegex(field.getAnnotation(IsParameterPresent.class).name())))
                    .findFirst().orElseThrow(NoAssociatedSetterException::new);

            try {
                if (this.purifiedArgsSet.contains(field.getAnnotation(IsParameterPresent.class).name()))
                    setterMethod.invoke(this.paramInstance, true);
                else setterMethod.invoke(this.paramInstance, false);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new NoAssociatedSetterException();
            }
        }
    }

    public void checkForTextValueFields()
            throws NoAssociatedSetterException, ArgsStartingWithValueException, NoTextValueOnMandatoryFieldException {
        if (this.args.size() > 0) {
            if (!this.args.get(0).startsWith("-"))
                throw new ArgsStartingWithValueException();
        }

        for (Field field : this.container.getTextValueFields()) {

            Method setterMethod = this.methods.stream()
                    .filter(method -> method
                            .getName()
                            .matches(textValueSetterRegex(field.getAnnotation(TextValue.class).name())))
                    .findFirst().orElseThrow(NoAssociatedSetterException::new);

            try {
                if (this.purifiedArgsSet.contains(field.getAnnotation(TextValue.class).name())) {
                    int argPosition = this.args.indexOf(
                            String.format("-%s", field.getAnnotation(TextValue.class).name())) + 1;
                    StringBuilder textBuilder = new StringBuilder();
                    while (true) {
                        String textPart = this.args.get(argPosition);
                        if (!textPart.startsWith("-")) {
                            textBuilder.append(String.format("%s ", textPart));
                            if (argPosition + 1 == this.args.size()) break;
                            else argPosition++;
                        } else break;
                    }
                    if (textBuilder.length() != 0) {
                        textBuilder.deleteCharAt(textBuilder.length() - 1);
                    } else {
                        if (field.isAnnotationPresent(Mandatory.class) ||
                                container.getParamClass().isAnnotationPresent(Mandatory.class))
                            throw new NoTextValueOnMandatoryFieldException();
                    }
                    setterMethod.invoke(this.paramInstance, textBuilder.toString());
                }
            } catch (IllegalAccessException | InvocationTargetException  e) {
                throw new NoAssociatedSetterException();
            }
        }
    }

    public void checkForMandatoryFields() throws MandatoryFieldNotPresentException {
        Set <String> mandatoryFieldNames = container
                .getMandatoryFields().stream()
                .map(mandatoryField -> mandatoryField.getAnnotation(TextValue.class).name())
                .collect(Collectors.toSet());
        Set <String> argsNamesSet = this.purifiedArgsSet.stream()
                .filter(this.container
                        .getTextValueFields().stream()
                        .map(textValueField -> textValueField.getAnnotation(TextValue.class).name())
                        .collect(Collectors.toSet())::contains)
                .collect(Collectors.toSet());

        if (!argsNamesSet.containsAll(mandatoryFieldNames))
            throw new MandatoryFieldNotPresentException();
    }

    private static String isParameterPresentSetterRegex(String purifiedArg) {
        return String.format("set(?i)(%s){1}(?-i)Present$", purifiedArg);
    }

    private static String textValueSetterRegex(String purifiedArg) {
        return String.format("set(?i)(%s){1}(?-i)Value$", purifiedArg);
    }


}
