import { DocAddress } from './doc-address.model';
import { DoctorAddress } from './doctor-address.model';

export class Appointment {
    doctorName: string = "Not filled";
    date: Date;
    clinicName: string = "Not filled";
    address: DoctorAddress;
    slot: string = "Not filled";
    appointmentId: number;

    constructor(doctorName: string, date: Date, clinicName: string, address: DoctorAddress, slot: string, appointmentId: number) {
        this.doctorName = doctorName;
        this.date = date;
        this.clinicName = clinicName;
        this.address = address;
        this.slot = slot;
        this.appointmentId = appointmentId;

    }
}
