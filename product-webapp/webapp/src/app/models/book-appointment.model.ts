import { Doctor } from "./doctor.model";
import { User } from "./user.model";

export class BookAppointment {
    id: string;
    doctor: Doctor;
    user: User;
    appointmentDate: Date;
    appointmentId: number;
    slot: string;

    constructor(id: string, doctor: Doctor, user: User, appointmentDate: Date, appointmentId: number, slot: string) {
        this.id = id;
        this.doctor = doctor;
        this.user = user;
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.slot = slot;
    }
}
