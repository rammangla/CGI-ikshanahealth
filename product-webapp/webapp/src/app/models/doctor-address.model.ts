export class DoctorAddress {
    clinicName: String;
    plotNo: String;
    street: String;
    town: String;
    district: String;
    state: String;
    pinCode: number;

    constructor(clinicName: String, plotNo: String, street: String, town: String, district: String, state: String, pinCode: number) {
        this.clinicName = clinicName;
        this.plotNo = plotNo;
        this.street = street;
        this.town=  town;
        this.district = district;
        this.state = state;
        this.pinCode = pinCode;
    }
}
