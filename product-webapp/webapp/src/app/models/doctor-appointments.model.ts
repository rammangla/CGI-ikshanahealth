import { DoctorPatientDetails } from "./doctor-patientDetails.model";

export class DoctorAppointments {
    date: Date;
    slot: String;
    patientDetails: DoctorPatientDetails;
    appointmentId: number;

    constructor(date: Date,  slot: string, patientDetails: DoctorPatientDetails, appointmentId: number) {
        this.date = date;
        this.slot = slot;
        this.patientDetails = patientDetails;
        this.appointmentId = appointmentId;

    }
}
