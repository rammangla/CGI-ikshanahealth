export class CaretakerAddress {
    plotNo: String;
    street: String;
    town: String;
    district: String;
    state: String;
    pincode: number;

    constructor(plotNo: String, street: String, town: String, district: String, state: String, pincode: number) {
        this.district = district;
        this.pincode = pincode;
        this.plotNo = plotNo;
        this.state = state;
        this.street = street;
        this.town=  town;
    }
}
