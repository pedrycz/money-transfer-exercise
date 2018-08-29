package cz.pedry.moneytransfer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;

import java.math.BigInteger;

import static cz.pedry.moneytransfer.model.Transfer.FROM;
import static cz.pedry.moneytransfer.model.Transfer.TO;

@Entity
@Table(name = "transfer", indexes = {@Index(columnList = FROM), @Index(columnList = TO)})
public class Transfer {

    private static final long ONE = 1;

    public static final String ID = "transfer_id";
    public static final String FROM = "transfer_from";
    public static final String TO = "transfer_to";
    public static final String AMOUNT = "transfer_amount";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = FROM, nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"transfersFrom", "transfersTo", "balance"})
    private Account from;

    @JoinColumn(name = TO, nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"transfersFrom", "transfersTo", "balance"})
    private Account to;

    @Min(ONE)
    @Column(name = AMOUNT, nullable = false)
    private BigInteger amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

}
