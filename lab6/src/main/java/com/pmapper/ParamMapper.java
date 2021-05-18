package com.pmapper;

import com.pmapper.annotations.IsParameterPresent;
import com.pmapper.annotations.Mandatory;
import com.pmapper.annotations.TextValue;
import com.pmapper.exceptions.*;

import java.lang.reflect.Field;
import java.util.Arrays;


public class ParamMapper {

    public <E> E map(String[] args, Class<E> paramClass) throws ParameterReadingException {

        E paramInstance = null;
        try {
            ParamMapperContainer container = readMetadata(paramClass);
            paramInstance = (E) readParameters(args, container);
        } catch (NoDefaultConstructorException | NoAssociatedSetterException | InvalidTypeException |
                 ArgsStartingWithValueException | MandatoryFieldNotAssociatedWithName |
                 MandatoryFieldNotPresentException | NoTextValueOnMandatoryFieldException e) {
            throw new ParameterReadingException(e);
        }
        return paramInstance;
    }

    private ParamMapperContainer readMetadata(Class<?> paramClass)
            throws InvalidTypeException, MandatoryFieldNotAssociatedWithName {

        // obtain annotations, check for allMandatory annotation
        boolean allMandatory = Arrays.stream(paramClass.getAnnotations()).anyMatch(annotation ->
                annotation.annotationType().equals(Mandatory.class));

        // create container instance
        ParamMapperContainer container = new ParamMapperContainer(paramClass, allMandatory);

        // obtain paramClass fields, put them into container, to appropriate categories
        Field[] fields = paramClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Mandatory.class)) container.addMandatoryField(field);
            if (field.isAnnotationPresent(IsParameterPresent.class)) container.addPresentField(field);
            if (field.isAnnotationPresent(TextValue.class)) container.addTextValueField(field);
        }
        return container;
    }

    private Object readParameters(String[] args, ParamMapperContainer container)
            throws NoDefaultConstructorException, NoAssociatedSetterException,
            ArgsStartingWithValueException, MandatoryFieldNotPresentException,
            NoTextValueOnMandatoryFieldException {

        ParamReader reader = new ParamReader(container, args);
        reader.initializeParamInstance();
        reader.checkForIsPresentFields();
        reader.checkForTextValueFields();
        reader.checkForMandatoryFields();
        return reader.getParamInstance();
    }

}
