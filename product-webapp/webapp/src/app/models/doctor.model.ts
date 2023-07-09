import { DoctorAddress } from "./doctor-address.model";
import { DoctorAppointments } from "./doctor-appointments.model";

export class Doctor {
    name: string = "Not filled";
    emailId: string = "Not filled";
    password: string = "Not filled";
    phoneNumber: number;
    dob: Date ;
    gender: string = "Not filled";
    idCard: string = "Not filled";
    educationalQualification: string = "Not filled";
    address: DoctorAddress ;
    licenseNumber: string = "Not filled";
    specialization: string = "Not filled";
    totalExperience: string = "Not filled";
    fees: string = "Not filled";
    appointments: DoctorAppointments[] = [];
    facilities: Array<string> = [];
    role:string = "doctor";
    
    constructor(name: string, emailId: string, dob: Date, gender: string, idCard: string, educationalQualification: string, address:DoctorAddress, liscenceNumber: string, phoneNumber: number, specialization: string, totalExperience: string, password: string) {
        this.name = name;
        this.emailId = emailId;
        this.dob = dob;
        this.gender = gender;
        this.idCard = idCard;
        this.educationalQualification = educationalQualification;
        this.address = address;
        this.licenseNumber = liscenceNumber;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.totalExperience = totalExperience;
        this.password = password;
        
    }
}
