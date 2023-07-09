import { Address } from './address.model';
import { Appointment } from './appointment.model';
import { CaretakerAppointments } from './caretaker-appointments.model';

export class User {
    name: string = "Not filled";
    emailId: string = "Not filled";
    password: string = "Not filled";
    phoneNumber: number;
    dob: Date;
    gender: string = "Not filled";
    address: Address = new Address();
    idCard: string = "Not filled";
    appointments: Appointment[] = [];
    caretakerAppointments: CaretakerAppointments[] = [];
    role: string = "user";
    constructor(name: string, emailId: string, password: string, dob: Date, gender: string) {
        this.name = name;
        this.emailId = emailId;
        this.dob = dob;
        this.gender = gender;
        this.password = password;
    }
}
