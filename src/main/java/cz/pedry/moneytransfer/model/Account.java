package cz.pedry.moneytransfer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigInteger.ZERO;

@Entity
@Table(name = "account")
public class Account {

    public static final String ID = "account_id";

    @Id
    @Column(name = ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "from", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"from", "to", "amount"})
    private List<Transfer> transfersFrom = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "to", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"from", "to", "amount"})
    private List<Transfer> transfersTo = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Transfer> getTransfersFrom() {
        return transfersFrom;
    }

    public void setTransfersFrom(List<Transfer> transfersFrom) {
        this.transfersFrom = transfersFrom;
    }

    public List<Transfer> getTransfersTo() {
        return transfersTo;
    }

    public void setTransfersTo(List<Transfer> transfersTo) {
        this.transfersTo = transfersTo;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public BigInteger getBalance() {
        return transfersTo.stream().map(Transfer::getAmount).reduce(ZERO, BigInteger::add).subtract(
                transfersFrom.stream().map(Transfer::getAmount).reduce(ZERO, BigInteger::add));
    }

}
