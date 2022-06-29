package eu.nnn4.acleaneva.app.common.exception;

import eu.nnn4.acleaneva.app.common.PropertiesConfig;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.util.Optional;

@RequiredArgsConstructor
public class AppRuntimeException {

    private static PropertiesConfig propertiesConfig;

    public static RuntimeException throwException(String messageTemplate, String... args) {
        return new RuntimeException(format(messageTemplate, args));
    }

    public static RuntimeException throwException(EModelName modelName, EExceptionType exceptionType, String... args) {
        String messageTemplate = getMessageTemplate(modelName, exceptionType);
        return throwException(exceptionType, messageTemplate, args);
    }

    public static RuntimeException throwExceptionWithId(EModelName modelName, EExceptionType exceptionType, Integer id, String... args) {
        String messageTemplate = getMessageTemplate(modelName, exceptionType).concat(".").concat(id.toString());
        return throwException(exceptionType, messageTemplate, args);
    }

    public static RuntimeException throwExceptionWithTemplate(EModelName modelName, EExceptionType exceptionType, String messageTemplate, String... args) {
        return throwException(exceptionType, messageTemplate, args);
    }

//    referenced methods/helpers

    private static RuntimeException throwException(EExceptionType exceptionType, String messageTemplate, String... args) {
        if (EExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(messageTemplate, args));
        } else if (EExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(format(messageTemplate, args));
        }
        return new RuntimeException(format(messageTemplate, args));
    }

    private static String getMessageTemplate(EModelName modelName, EExceptionType exceptionType) {
        return modelName.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }

    private static String format(String template, String ... args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
        if (templateContent.isPresent()) {
            return MessageFormat.format(templateContent.get(), (Object[]) args);
        }
        return String.format(template, (Object[]) args);
    }

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }
}
