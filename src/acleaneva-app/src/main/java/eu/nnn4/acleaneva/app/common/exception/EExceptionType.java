package eu.nnn4.acleaneva.app.common.exception;

public enum EExceptionType {
    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    ENTITY_EXCEPTION("exception");

    String value;

    EExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}
