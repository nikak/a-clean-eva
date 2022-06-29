package eu.nnn4.acleaneva.domain;

import lombok.Getter;

import java.util.stream.Stream;

public enum EAccountType {
    PERSONAL(1), COMPANY(100);
    @Getter private Integer code;

    EAccountType(int i) {
        this.code =i;
    }

    public static EAccountType of(int code) {
        return Stream.of(EAccountType.values())
                .filter(p -> p.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
