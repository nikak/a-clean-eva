package eu.nnn4.acleaneva.domain;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import javax.persistence.AttributeConverter;
//import java.util.Currency;

public class MoneyConverter  implements AttributeConverter<Money, EmbeddableMoney> {

    @Override
    public EmbeddableMoney convertToDatabaseColumn(Money attribute) {
        if (attribute == null) {
            return null;
        }
        EmbeddableMoney columns = new EmbeddableMoney();
        columns.setValue(attribute.getAmount());
        columns.setUnit(ECurrencyCode.valueOf(attribute.getCurrencyUnit().getCode()));
//        columns.setUnit(attribute.getCurrencyUnit().toCurrency());
        return columns;
    }

    @Override
    public Money convertToEntityAttribute(EmbeddableMoney dbData) {
        if (dbData == null) {
            return null;
        }
        return Money.of(CurrencyUnit.of(dbData.getUnit().name()),dbData.getValue());
    }
}
