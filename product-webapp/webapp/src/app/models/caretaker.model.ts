import { CaretakerAddress } from "./caretaker-address.model";
import { CaretakerAppointments } from "./caretaker-appointments.model";

export class Caretaker {
    name: string = "Not filled";
    emailId: string = "Not filled";
    password: string = "Not filled";
    dob: Date;
    gender: string = "Not filled";
    address: CaretakerAddress;
    idCard: string = "Not filled";
    totalExperience: string = "Not filled";
    fees: string = "Not filled";
    phoneNumber: string = "Not filled";
    appointments: CaretakerAppointments[] = [];
    bookedAppointments: CaretakerAppointments[] = [];
    services: Array<string> = [];
    role: string = "caretaker";
    specialization: string;

    constructor(name: string, emailId: string, password: string, dob: Date, gender: string, address: CaretakerAddress, idCard: string, totalExperience: string, phoneNumber: string, specialization: string) {
        this.name = name;
        this.emailId = emailId;
        this.dob = dob;
        this.gender = gender;
        this.password = password;
        this.address = address;
        this.idCard = idCard;
        this.totalExperience = totalExperience;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;

    }
}
