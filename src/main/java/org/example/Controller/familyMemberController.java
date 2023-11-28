package org.example.Controller;

import org.example.Model.Class.familyMember;
import java.util.ArrayList;

public class familyMemberController {

    private static ArrayList<familyMember> familyMembers = new ArrayList<>();

    public static void addFamilyMember(familyMember fM){

        familyMembers.add(fM);
    }

    public static void print(){
        for (int i = 0; i < familyMembers.size(); i++){
            System.out.println(familyMembers.get(i));
        }
    }

    public static ArrayList<familyMember> getFamilyMembers() {
        return familyMembers;
    }



}
