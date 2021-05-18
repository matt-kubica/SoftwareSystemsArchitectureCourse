package com.pmapper;

import com.pmapper.annotations.IsParameterPresent;
import com.pmapper.annotations.TextValue;
import com.pmapper.exceptions.InvalidTypeException;
import com.pmapper.exceptions.MandatoryFieldNotAssociatedWithName;

import java.lang.reflect.Field;
import java.util.*;

public class ParamMapperContainer {

    private Class<?> paramClass = null;

    public Class<?> getParamClass() {
        return paramClass;
    }

    private final List<Field> mandatoryFields = new ArrayList<>();
    private final List<Field> presentFields = new ArrayList<>();
    private final List<Field> textValueFields = new ArrayList<>();


    public ParamMapperContainer(Class<?> paramClass, boolean allMandatory)
            throws MandatoryFieldNotAssociatedWithName {

        this.paramClass = paramClass;
        if (allMandatory) {
            for (Field field : paramClass.getDeclaredFields())
                this.addMandatoryField(field);
        }


    }

    public List<Field> getMandatoryFields() {
        return mandatoryFields;
    }

    public void addMandatoryField(Field field) throws MandatoryFieldNotAssociatedWithName {
        if (field.isAnnotationPresent(TextValue.class)) {
            if (!this.mandatoryFields.contains(field))
                this.mandatoryFields.add(field);
        } else {
            throw new MandatoryFieldNotAssociatedWithName(String
                    .format("There is no arg name associated with mandatory field '%s'", field.getName()));
        }

    }

    public List<Field> getPresentFields() {
        return presentFields;
    }

    public void addPresentField(Field field) throws InvalidTypeException {
        if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
            if (!this.presentFields.contains(field))
                this.presentFields.add(field);
        } else {
            throw new InvalidTypeException(String
                    .format("Field '%s' associated with '%s' annotation is of type '%s'",
                            field.getName(),
                            IsParameterPresent.class.getName(),
                            field.getType().getName()
                    )
            );
        }

    }

    public List <Field> getTextValueFields() {
        return textValueFields;
    }

    public void addTextValueField(Field field) throws InvalidTypeException {
        if (field.getType().equals(String.class)) {
            if (!this.textValueFields.contains(field))
                this.textValueFields.add(field);
        } else {
            throw new InvalidTypeException(String
                    .format("Field '%s' associated with '%s' annotation is of type '%s'",
                            field.getName(),
                            IsParameterPresent.class.getName(),
                            field.getType().getName()
                    )
            );
        }


    }
}
