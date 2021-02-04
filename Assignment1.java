package com.exmple;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

//Assignment 1
//Ishan Gupta
//2019308
//CSD-2ndyear SecA

public class Assignment1 {

    public static int unadmittedPatients=0;
    public static ArrayList<Patient> patientDatabase;
    public static ArrayList<Hospital> hospitalDatabase;

    //Query 1
    public static void checkAdmissions(Hospital hospital){

        int count=0;
        //Oxygen Check
        while (hospital.noOfBeds>0 && count<patientDatabase.size()){
            Patient patient = patientDatabase.get(count);
            if (!patient.admitted && patient.oxygenLevel>= hospital.oxygenLevel){
                hospital.admission(patient);
                unadmittedPatients--;
            }
            count++;
        }
        count = 0;
        //Temperature Check
        while (hospital.noOfBeds>0 && count<patientDatabase.size()){
            Patient patient = patientDatabase.get(count);
            if (!patient.admitted && patient.temp<= hospital.temp){
                hospital.admission(patient);
                unadmittedPatients--;
            }
            count++;
        }
    }

    //Query 2
    public static void deleteAdmittedPatients(){
        int count=0;
        while(count<patientDatabase.size()){
            if (patientDatabase.get(count).admitted){
                System.out.println(patientDatabase.get(count).ID);
                patientDatabase.remove(count);
            }else{
                count++;
            }
        }/*
        count=0;
        while (count<hospitalDatabase.size()){
            hospitalDatabase.get(count).admitted.clear();
            count++;
        }*/
    }

    //Query 3
    public static void deleteClosedHospitals(){
        int count=0;
        while (count<hospitalDatabase.size()){
            if (hospitalDatabase.get(count).noOfBeds==0){
                System.out.println(hospitalDatabase.get(count).name);
                hospitalDatabase.remove(count);
            }else{
                count++;
            }
        }
    }

    //Query 5
    public static int openHospitals(){
        int ans=0;
        int count=0;
        while(count<hospitalDatabase.size()){
            if (hospitalDatabase.get(count).noOfBeds!=0){
                ans++;
            }
            count++;
        }
        return ans;
    }

    //Query 6
    public static void detailsOfHospital(String hospitalName){
        int count=0;
        while(count<hospitalDatabase.size()){
            if (hospitalDatabase.get(count).name.equals(hospitalName)){
                System.out.println(hospitalDatabase.get(count).name);
                System.out.println("Temperature should be <= "+ hospitalDatabase.get(count).temp);
                System.out.println("Oxygen levels should be >= "+ hospitalDatabase.get(count).oxygenLevel);
                System.out.println("Number of Available beds – "+ hospitalDatabase.get(count).noOfBeds);
                if (hospitalDatabase.get(count).noOfBeds==0){
                    System.out.println("Admission Status – CLOSED");
                }else{
                    System.out.println("Admission Status – OPEN");
                }
                break;
            }
            count++;
        }
    }

    //Query 7
    public static void detailsOfPatient(int patientID){
        int count=0;
        while(count<patientDatabase.size()){
            if (patientDatabase.get(count).ID==patientID){
                System.out.println(patientDatabase.get(count).name);
                System.out.println("Temperature is "+patientDatabase.get(count).temp);
                System.out.println("Oxygen levels is "+ patientDatabase.get(count).oxygenLevel);
                if (patientDatabase.get(count).admitted) {
                    System.out.println("Admission Status – Admitted");
                    System.out.println("Admitting Institute - "+ patientDatabase.get(count).hospitalName);
                }else{
                    System.out.println("Admission Status – Not Admitted");
                }

                break;
            }
            count++;
        }

    }

    //Query 8
    public static void displayAllPatients(){
        int count=0;
        while (count<patientDatabase.size()){
            System.out.println(patientDatabase.get(count).ID +" "+ patientDatabase.get(count).name);
            count++;
        }
    }

    //Query 9
    public  static  void patientsInHospital(String hospitalName){
        int count=0;
        while(count<hospitalDatabase.size()){
            if (hospitalDatabase.get(count).name.equals(hospitalName)){
                int i=0;
                while(i<hospitalDatabase.get(count).admitted.size()) {
                    System.out.println(hospitalDatabase.get(count).admitted.get(i).name +", recovery time is " + hospitalDatabase.get(count).admitted.get(i).recoveryTime+" days");
                    i++;
                }
                break;
            }
            count++;
        }
    }

    public static void main(String args[]){
        Scanner m = new Scanner(System.in);
        patientDatabase= new ArrayList<Patient>();
        hospitalDatabase= new ArrayList<Hospital>();
        int noOfPatients = m.nextInt();
        unadmittedPatients =noOfPatients;

        for (int i=0;i<noOfPatients;i++){
            Patient obj = new Patient(m.next(),m.nextFloat(), m.nextInt(), m.nextInt());
            patientDatabase.add(obj);
            //System.out.println("Patient ID= "+obj.ID);
        }

        while (unadmittedPatients!=0){
            int Query = m.nextInt();
            if (Query==1){//Add Hospital in hospitalDatabase

                String name = m.next();
                System.out.println("Temperature Criteria – ");
                float temp = m.nextFloat();
                System.out.println("Oxygen Levels – ");
                int oxygenLevel = m.nextInt();
                System.out.println("Number of Available beds – ");
                int beds = m.nextInt();
                Hospital obj = new Hospital(name,temp,oxygenLevel,beds);
                hospitalDatabase.add(obj);

                System.out.println(obj.name+"\n"+"Temperature should be <= "+ obj.temp+"\n"+ "Oxygen levels should be >= "+ obj.oxygenLevel +"\n"+ "Number of Available beds – "+obj.noOfBeds+"\n"+"Admission Status – OPEN");
                checkAdmissions(obj);
                for (int i=0;i< obj.admitted.size();i++) {
                    System.out.println("Recovery days for admitted patient ID "+ obj.admitted.get(i).ID + " - ");
                    obj.admitted.get(i).recoveryTime= m.nextInt();
                }

            }

            else if (Query==2){
                System.out.println("Account ID removed of admitted patients");
                deleteAdmittedPatients();
            }

            else if (Query==3){
                System.out.println("Accounts removed of Institute whose admission is closed");
                deleteClosedHospitals();
            }

            else if (Query==4){
                System.out.println(unadmittedPatients+ " patients");
            }

            else if (Query==5){
                System.out.println(openHospitals() + " institutes are admitting patients currently");
            }

            else if (Query==6){
                detailsOfHospital(m.next());

            }

            else if (Query==7){
                detailsOfPatient(m.nextInt());
            }

            else if (Query==8){
                displayAllPatients();
            }

            else if (Query==9){
                patientsInHospital(m.next());
            }
        }

    }

}

class Patient{
    String name;
    float temp;
    int oxygenLevel;
    int age;
    int ID;
    boolean admitted=false;
    int recoveryTime;
    String hospitalName;
    private static int numberOfPatient =0;
    public Patient(String n, float t, int o,int a){
        numberOfPatient++;
        this.name=n;
        this.temp=t;
        this.oxygenLevel=o;
        this.age=a;
        this.ID=numberOfPatient;
    }
}

class Hospital{
    String name;
    float temp;
    int oxygenLevel;
    int noOfBeds;
    ArrayList<Patient> admitted=new ArrayList<Patient>();
    public Hospital(String n, float t, int o,int b) {
        this.name = n;
        this.noOfBeds = b;
        this.oxygenLevel = o;
        this.temp = t;
    }
    public void admission(Patient patient){
        admitted.add(patient);
        patient.admitted=true;
        patient.hospitalName=name;
        noOfBeds--;
    }
}
