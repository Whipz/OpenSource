package scripts.dax_api.api_lib.models;

import com.allatori.annotations.DoNotRename;

@DoNotRename
public class BankPathRequestPair {
    @DoNotRename
    private final Point3D start;
    @DoNotRename
    private final RunescapeBank bank;

    public BankPathRequestPair(Point3D start, RunescapeBank bank) {
        this.start = start;
        this.bank = bank;
    }

    public Point3D getStart() {
        return start;
    }

    public RunescapeBank getBank() {
        return bank;
    }
}
