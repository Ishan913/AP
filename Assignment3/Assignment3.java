package Ass3;
import java.util.*;

public class Assignment3 {
    public static void main(String[] args) {
        new initiate();
    }
}

class initiate{
    public initiate(){
        Scanner m= new Scanner(System.in);
        System.out.println("Welcome to Mafia\nEnter Number of players: ");
        int noOfPLayers = m.nextInt();
        while(noOfPLayers<6){
            System.out.println("Welcome to Mafia\nEnter Number of players: ");
            noOfPLayers = m.nextInt();
        }
        Random rand = new Random();
        System.out.println("Choose a Character\n" +
                "1) Mafia\n" +
                "2) Detective\n" +
                "3) Healer\n" +
                "4) Commoner\n" +
                "5) Assign Randomly");
        int noOfDetect = noOfPLayers/5;
        int noOfMafia = noOfDetect;
        int noOfHealers = Math.max(1,noOfPLayers/10);
        int noOfCommoners = noOfPLayers-noOfDetect-noOfHealers-noOfMafia;
        int[] database = new int[4];
        database[0]=noOfCommoners;
        database[1]=noOfDetect;
        database[2]=noOfHealers;
        database[3]=noOfMafia;

        Player player = new Player();
        Mafia mafia = new Mafia();
        Commoner commoner = new Commoner();
        Healer healer = new Healer();
        Detective detective = new Detective();

        for (int i=0;i<noOfPLayers;i++){
            int curr = rand.nextInt(4);
            while(database[curr]==0){
                curr = rand.nextInt(4);
            }
            database[curr]--;
            if (curr==0){
                player.addRole(new Commoner());
                commoner.addRoles(i);
            }
            if (curr==1){
                player.addRole(new Detective());
                detective.addRoles(i);
            }
            if (curr==2){
                player.addRole(new Healer());
                healer.addRoles(i);
            }
            if (curr==3){
                player.addRole(new Mafia());
                mafia.addRoles(i);
            }
        }
        int type = m.nextInt();
        User user =new User();

        if (type==1){
            for (int i=0;i<noOfPLayers;i++){
                if (player.getRole(i).getClass().equals(new Mafia().getClass())){
                    user.setIndex(i);
                    user.setRole(new Mafia());
                    break;
                }
            }
        }
        if (type==2){
            for (int i=0;i<noOfPLayers;i++){
                if (player.getRole(i).getClass().equals(new Detective().getClass())){
                    user.setIndex(i);
                    user.setRole(new Detective());
                    break;
                }
            }
        }
        if (type==3){
            for (int i=0;i<noOfPLayers;i++){
                if (player.getRole(i).getClass().equals(new Healer().getClass())){
                    user.setIndex(i);
                    user.setRole(new Healer());
                    break;
                }
            }
        }
        if (type==4){
            for (int i=0;i<noOfPLayers;i++){
                if (player.getRole(i).getClass().equals(new Commoner().getClass())){
                    user.setIndex(i);
                    user.setRole(new Commoner());
                    break;
                }
            }
        }
        if (type==5){
            int x = rand.nextInt(10);
            user.setRole(player.getRole(x));
            for (int i=0;i<noOfPLayers;i++){
                if (player.getRole(i).getClass().equals(user.getRole().getClass())){
                    user.setIndex(i);
                    break;
                }
            }

        }

        System.out.println("You are Player"+(user.getIndex()+1));
        String ro = user.getRole().getClass().getSimpleName();
        System.out.print("You are a "+ro+". Other "+ro+"s are: ");
        if (ro.equals(mafia.getClass().getSimpleName())){
            for (int j=0;j<mafia.getArraySize();j++){
                if (j!=0)
                    System.out.print("[Player"+(mafia.getRoles(j)+1)+"]");
            }
        }else if(ro.equals(detective.getClass().getSimpleName())){
            for (int j=0;j<detective.getArraySize();j++){
                if (j!=0)
                    System.out.print("[Player"+(detective.getRoles(j)+1)+"]");
            }
        }else if(ro.equals(healer.getClass().getSimpleName())){
            for (int j=0;j<healer.getArraySize();j++){
                if (j!=0)
                    System.out.print("[Player"+(healer.getRoles(j)+1)+"]");
            }
        }else if(ro.equals(commoner.getClass().getSimpleName())){
            for (int j=0;j<commoner.getArraySize();j++){
                if (j!=0)
                    System.out.print("[Player"+(commoner.getRoles(j)+1)+"]");
            }
        }

        int roundNo =1;
        while(true){
            if (mafia.getArraySize()==0) {
                System.out.println("\nGame Over.\nThe mafias have lost.");
                for (int x=0;x<noOfPLayers;x++){
                    if (player.getRole(x).getClass().equals(mafia.getClass())) {
                        System.out.print("Player"+(x+1)+", ");
                    }
                }
                System.out.print("were Mafias\n");
                for (int x=0;x<noOfPLayers;x++){
                    if (player.getRole(x).getClass().equals(detective.getClass())) {
                        System.out.print("Player"+(x+1)+", ");
                    }
                }
                System.out.print("were Detectives\n");
                for (int x=0;x<noOfPLayers;x++){
                    if (player.getRole(x).getClass().equals(healer.getClass())) {
                        System.out.print("Player"+(x+1)+", ");
                    }
                }
                System.out.print("were Healers\n");
                for (int x=0;x<noOfPLayers;x++){
                    if (player.getRole(x).getClass().equals(commoner.getClass())) {
                        System.out.print("Player"+(x+1)+", ");
                    }
                }
                System.out.print("were Commoners\n");
                break;
            }
            if (mafia.getArraySize()==(detective.getArraySize()+commoner.getArraySize()+healer.getArraySize())){
                System.out.println("\nGame Over.\nThe mafias have won.");
                for (int x=0;x<noOfPLayers;x++){
                    if (player.getRole(x).getClass().equals(mafia.getClass())) {
                        System.out.print("Player"+(x+1)+", ");
                    }
                }
                System.out.print("were Mafias\n");
                for (int x=0;x<noOfPLayers;x++){
                    if (player.getRole(x).getClass().equals(detective.getClass())) {
                        System.out.print("Player"+(x+1)+", ");
                    }
                }
                System.out.print("were Detectives\n");
                for (int x=0;x<noOfPLayers;x++){
                    if (player.getRole(x).getClass().equals(healer.getClass())) {
                        System.out.print("Player"+(x+1)+", ");
                    }
                }
                System.out.print("were Healers\n");
                for (int x=0;x<noOfPLayers;x++){
                    if (player.getRole(x).getClass().equals(commoner.getClass())) {
                        System.out.print("Player"+(x+1)+", ");
                    }
                }
                System.out.print("were Commoners\n");
                break;
            }

//            for (int q=0;q<10;q++){
//                System.out.println(player.getRole(q).getClass().getSimpleName()+" hp: "+player.getRole(q).getPlayerHP());
//            }

            System.out.println("\nRound "+roundNo+":");
            roundNo++;
            System.out.println(player.getNoOfAlive()+" Players are remaining: ");
            int initialAlive=player.getNoOfAlive();
            for (int x=0;x<player.getAlive().size();x++){
                if (player.getAlive().get(x)==1){
                    System.out.print("Player"+(x+1)+", ");
                }
            }
            System.out.print(" are alive.\n");

            //MAFIA USER
            if (user.getRole().getClass().equals(new Mafia().getClass())){

                if (player.getAlive().get(user.getIndex())==1) {//mafia yes user yes
                    System.out.println("Choose a target: ");
                    int tar0 = m.nextInt() -1;//target player num chosen by mafia
                    while (player.getAlive().get(tar0)!=1 || player.getRole(tar0).getClass().equals(mafia.getClass())){
                        System.out.println("Choose a target: ");
                        tar0 = m.nextInt()-1;
                    }
                    Player killPlayer = player.getRole(tar0);
                    mafia.killHP(killPlayer,tar0);

                    if (detective.getArraySize()>0) {//mafia yes detective yes
                        int tar = detective.chooseRandom();
                        System.out.println("Detectives have chosen a player to test.");

                        if (healer.getArraySize() > 0) {
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        }
                        System.out.println("Healers have chosen someone to heal.");

                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        if (player.getRole(tar).getClass().equals(mafia.getClass())){
                            System.out.println("Player"+(tar+1)+" has been voted out.");
                            Player pee = player.getRole(tar);
                            pee.setHP(0);
                            player.killInBigList(tar);;
                            if (pee.getClass().equals(detective.getClass())){
                                detective.removePlayerByCode(tar);
                            }if (pee.getClass().equals(healer.getClass())){
                                healer.removePlayerByCode(tar);
                            }if (pee.getClass().equals(commoner.getClass())){
                                commoner.removePlayerByCode(tar);
                            }if (pee.getClass().equals(mafia.getClass())){
                                mafia.removePlayerByCode(tar);
                            }
                        }
                        else{
                            System.out.println("Select a person to vote out: ");
                            int tar4 = m.nextInt()-1;//target player num chosen by commoner
                            while (player.getAlive().get(tar4)!=1){
                                System.out.println("Select a person to vote out: ");
                                tar4 = m.nextInt() -1;
                            }
                            int tar5 = commoner.chooseRandom();
                            System.out.println("Player"+(tar5 +1)+" has been voted out");
                            Player pee = player.getRole(tar5);
                            pee.setHP(0);
                            player.killInBigList(tar5);;
                            if (pee.getClass().equals(detective.getClass())){
                                detective.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(healer.getClass())){
                                healer.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(commoner.getClass())){
                                commoner.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(mafia.getClass())){
                                mafia.removePlayerByCode(tar5);
                            }
                        }

                    }
                    else{//mafia yes detective no
                        System.out.println("Detectives have chosen a player to test.");
                        if (healer.getArraySize() > 0) {
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        }
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        System.out.println("Select a person to vote out: ");
                        int tar4 = m.nextInt()-1;//target player num chosen by commoner
                        while (player.getAlive().get(tar4)!=1){
                            System.out.println("Select a person to vote out: ");
                            tar4 = m.nextInt() -1;
                        }
                        int tar5 = commoner.chooseRandom();
                        System.out.println("Player"+(tar5 +1)+" has been voted out");
                        Player pee = player.getRole(tar5);
                        pee.setHP(0);
                        player.killInBigList(tar5);;
                        if (pee.getClass().equals(detective.getClass())){
                            detective.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(healer.getClass())){
                            healer.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(commoner.getClass())){
                            commoner.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(mafia.getClass())){
                            mafia.removePlayerByCode(tar5);
                        }

                    }



                }
                else{//mafia yes user no
                    int tar0 = mafia.chooseRandom();
                    System.out.println("Mafias have chosen their target");

                    if (detective.getArraySize()>0) {//mafia yes user no detective yes
                        int tar = detective.chooseRandom();
                        System.out.println("Detectives have chosen a player to test.");

                        if (healer.getArraySize() > 0) {
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        }
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        if (player.getRole(tar).getClass().equals(mafia.getClass())){
                            System.out.println("Player"+(tar+1)+" has been voted out.");
                            Player pee = player.getRole(tar);
                            pee.setHP(0);
                            player.killInBigList(tar);;
                            if (pee.getClass().equals(detective.getClass())){
                                detective.removePlayerByCode(tar);
                            }if (pee.getClass().equals(healer.getClass())){
                                healer.removePlayerByCode(tar);
                            }if (pee.getClass().equals(commoner.getClass())){
                                commoner.removePlayerByCode(tar);
                            }if (pee.getClass().equals(mafia.getClass())){
                                mafia.removePlayerByCode(tar);
                            }
                        }
                        else{
                            int tar5 = commoner.chooseRandom();
                            System.out.println("Player"+(tar5 +1)+" has been voted out");
                            Player pee = player.getRole(tar5);
                            pee.setHP(0);
                            player.killInBigList(tar5);;
                            if (pee.getClass().equals(detective.getClass())){
                                detective.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(healer.getClass())){
                                healer.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(commoner.getClass())){
                                commoner.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(mafia.getClass())){
                                mafia.removePlayerByCode(tar5);
                            }
                        }

                    }
                    else{//mafia yes user no detective no
                        System.out.println("Detectives have chosen a player to test.");

                        if (healer.getArraySize() > 0) {
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        }
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        int tar5 = commoner.chooseRandom();
                        System.out.println("Player"+(tar5 +1)+" has been voted out");
                        Player pee = player.getRole(tar5);
                        pee.setHP(0);
                        player.killInBigList(tar5);;
                        if (pee.getClass().equals(detective.getClass())){
                            detective.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(healer.getClass())){
                            healer.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(commoner.getClass())){
                            commoner.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(mafia.getClass())){
                            mafia.removePlayerByCode(tar5);
                        }
                    }
                }
            }

            //DETECTIVE USER
            else if (user.getRole().getClass().equals(new Detective().getClass())){
                if (mafia.getArraySize() > 0) {//mafia present
                    int tar0 = mafia.chooseRandom();
                    System.out.println("Mafias have chosen their target");

                    if (player.getAlive().get(user.getIndex()) == 1){//mafia yes user yes

                        System.out.println("Choose a player to test: ");
                        int tar = m.nextInt() -1;//target player num chosen by detective
                        while (player.getAlive().get(tar)!=1 || player.getRole(tar).getClass().equals(detective.getClass())){
                            System.out.println("Choose a player to test: ");
                            tar = m.nextInt() -1;
                        }

                        if (healer.getArraySize()>0) {
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        }
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        if (player.getRole(tar).getClass().equals(mafia.getClass())){
                            System.out.println("Player" + (tar + 1) + " has been voted out.");
                            Player pee = player.getRole(tar);
                            pee.setHP(0);
                            player.killInBigList(tar);
                            ;
                            if (pee.getClass().equals(detective.getClass())) {
                                detective.removePlayerByCode(tar);
                            }
                            if (pee.getClass().equals(healer.getClass())) {
                                healer.removePlayerByCode(tar);
                            }
                            if (pee.getClass().equals(commoner.getClass())) {
                                commoner.removePlayerByCode(tar);
                            }
                            if (pee.getClass().equals(mafia.getClass())) {
                                mafia.removePlayerByCode(tar);
                            }
                        }
                        else{
                            System.out.println("Select a person to vote out: ");
                            int tar4 = m.nextInt() - 1;//target player num chosen by commoner
                            while (player.getAlive().get(tar4) != 1) {
                                System.out.println("Select a person to vote out: ");
                                tar4 = m.nextInt() - 1;
                            }
                            int tar5 = commoner.chooseRandom();
                            System.out.println("Player" + (tar5 + 1) + " has been voted out");
                            Player pee = player.getRole(tar5);
                            pee.setHP(0);
                            player.killInBigList(tar5);
                            ;
                            if (pee.getClass().equals(detective.getClass())) {
                                detective.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(healer.getClass())) {
                                healer.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(commoner.getClass())) {
                                commoner.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(mafia.getClass())) {
                                mafia.removePlayerByCode(tar5);
                            }

                        }


                    }
                    else if (detective.getArraySize() > 0){//mafia yes detective yes
                        int tar = detective.chooseRandom();
                        System.out.println("Detectives have chosen a player to test.");

                        if (healer.getArraySize()>0) {
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        }
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        if (player.getRole(tar).getClass().equals(mafia.getClass())){
                            System.out.println("Player" + (tar + 1) + " has been voted out.");
                            Player pee = player.getRole(tar);
                            pee.setHP(0);
                            player.killInBigList(tar);
                            ;
                            if (pee.getClass().equals(detective.getClass())) {
                                detective.removePlayerByCode(tar);
                            }
                            if (pee.getClass().equals(healer.getClass())) {
                                healer.removePlayerByCode(tar);
                            }
                            if (pee.getClass().equals(commoner.getClass())) {
                                commoner.removePlayerByCode(tar);
                            }
                            if (pee.getClass().equals(mafia.getClass())) {
                                mafia.removePlayerByCode(tar);
                            }
                        }
                        else{
                            int tar5 = commoner.chooseRandom();
                            System.out.println("Player" + (tar5 + 1) + " has been voted out");
                            Player pee = player.getRole(tar5);
                            pee.setHP(0);
                            player.killInBigList(tar5);
                            ;
                            if (pee.getClass().equals(detective.getClass())) {
                                detective.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(healer.getClass())) {
                                healer.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(commoner.getClass())) {
                                commoner.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(mafia.getClass())) {
                                mafia.removePlayerByCode(tar5);
                            }
                        }

                    }
                    else{//mafia yes detective no
                        System.out.println("Detectives have chosen a player to test.");

                        if (healer.getArraySize()>0) {
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        }
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        int tar5 = commoner.chooseRandom();
                        System.out.println("Player" + (tar5 + 1) + " has been voted out");
                        Player pee = player.getRole(tar5);
                        pee.setHP(0);
                        player.killInBigList(tar5);
                        ;
                        if (pee.getClass().equals(detective.getClass())) {
                            detective.removePlayerByCode(tar5);
                        }
                        if (pee.getClass().equals(healer.getClass())) {
                            healer.removePlayerByCode(tar5);
                        }
                        if (pee.getClass().equals(commoner.getClass())) {
                            commoner.removePlayerByCode(tar5);
                        }
                        if (pee.getClass().equals(mafia.getClass())) {
                            mafia.removePlayerByCode(tar5);
                        }

                    }

                }





                int tar0 = mafia.chooseRandom();
                System.out.println("Mafias have chosen their target");

                if (player.getAlive().get(user.getIndex())==1) {//user is alive

                    System.out.println("Choose a player to test: ");
                    int tar = m.nextInt() -1;//target player num chosen by detective
                    while (player.getAlive().get(tar)!=1 || player.getRole(tar).getClass().equals(detective.getClass())){
                        System.out.println("Choose a player to test: ");
                        tar = m.nextInt() -1;
                    }

                    if (player.getRole(tar).getClass().equals(mafia.getClass())){
                        System.out.println("Player"+(tar+1)+" is a mafia");
                        int tar2 = healer.chooseRandom();
                        Player pp = player.getRole(tar2);
                        pp.increaseHP(500);
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }
                        System.out.println("Player"+(tar+1)+" has been voted out.");
                        Player pee = player.getRole(tar);
                        pee.setHP(0);
                        player.killInBigList(tar);;
                        if (pee.getClass().equals(detective.getClass())){
                            detective.removePlayerByCode(tar);
                        }if (pee.getClass().equals(healer.getClass())){
                            healer.removePlayerByCode(tar);
                        }if (pee.getClass().equals(commoner.getClass())){
                            commoner.removePlayerByCode(tar);
                        }if (pee.getClass().equals(mafia.getClass())){
                            mafia.removePlayerByCode(tar);
                        }

                    }else{
                        System.out.println("Player"+(tar+1)+" is not a mafia");
                        int tar2 = healer.chooseRandom();
                        Player pp = player.getRole(tar2);
                        pp.increaseHP(500);
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        System.out.println("Select a person to vote out: ");
                        int tar4 = m.nextInt()-1;//target player num chosen by commoner
                        while (player.getAlive().get(tar4)!=1){
                            System.out.println("Select a person to vote out: ");
                            tar4 = m.nextInt() -1;
                        }
                        int tar5 = commoner.chooseRandom();
                        System.out.println("Player"+(tar5 +1)+" has been voted out");
                        Player pee = player.getRole(tar5);
                        pee.setHP(0);
                        player.killInBigList(tar5);;
                        if (pee.getClass().equals(detective.getClass())){
                            detective.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(healer.getClass())){
                            healer.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(commoner.getClass())){
                            commoner.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(mafia.getClass())){
                            mafia.removePlayerByCode(tar5);
                        }
                    }

                }
                else if(detective.getArraySize()!=0) {//other detective so automated

                    int tar = detective.chooseRandom();
                    System.out.println("Detectives have chosen a player to test.");

                    if (player.getRole(tar).getClass().equals(mafia.getClass())){
                        System.out.println("Player"+(tar+1)+" is a mafia");
                        int tar2 = healer.chooseRandom();
                        Player pp = player.getRole(tar2);
                        pp.increaseHP(500);
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }
                        System.out.println("Player"+(tar+1)+" has been voted out.");
                        Player pee = player.getRole(tar);
                        pee.setHP(0);
                        player.killInBigList(tar);;
                        if (pee.getClass().equals(detective.getClass())){
                            detective.removePlayerByCode(tar);
                        }if (pee.getClass().equals(healer.getClass())){
                            healer.removePlayerByCode(tar);
                        }if (pee.getClass().equals(commoner.getClass())){
                            commoner.removePlayerByCode(tar);
                        }if (pee.getClass().equals(mafia.getClass())){
                            mafia.removePlayerByCode(tar);
                        }

                    }else{
                        System.out.println("Player"+(tar+1)+" is not a mafia");
                        int tar2 = healer.chooseRandom();
                        Player pp = player.getRole(tar2);
                        pp.increaseHP(500);
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        System.out.println("Select a person to vote out: ");
                        int tar4 = m.nextInt()-1;//target player num chosen by commoner
                        while (player.getAlive().get(tar4)!=1){
                            System.out.println("Select a person to vote out: ");
                            tar4 = m.nextInt() -1;
                        }
                        int tar5 = commoner.chooseRandom();
                        System.out.println("Player"+(tar5 +1)+" has been voted out");
                        Player pee = player.getRole(tar5);
                        pee.setHP(0);
                        player.killInBigList(tar5);;
                        if (pee.getClass().equals(detective.getClass())){
                            detective.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(healer.getClass())){
                            healer.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(commoner.getClass())){
                            commoner.removePlayerByCode(tar5);
                        }if (pee.getClass().equals(mafia.getClass())){
                            mafia.removePlayerByCode(tar5);
                        }
                    }

                }
                else{
                    System.out.println("Detectives have chosen a player to test.");
                    int tar2 = healer.chooseRandom();
                    Player pp = player.getRole(tar2);
                    pp.increaseHP(500);
                    System.out.println("Healers have chosen someone to heal.");
                    if (initialAlive>player.getNoOfAlive()){
                        System.out.println("Player"+(tar0+1)+" has died");
                    }else{
                        System.out.println("No one died");
                    }

                    System.out.println("Select a person to vote out: ");
                    int tar4 = m.nextInt()-1;//target player num chosen by commoner
                    while (player.getAlive().get(tar4)!=1){
                        System.out.println("Select a person to vote out: ");
                        tar4 = m.nextInt() -1;
                    }
                    int tar5 = commoner.chooseRandom();
                    System.out.println("Player"+(tar5 +1)+" has been voted out");
                    Player pee = player.getRole(tar5);
                    pee.setHP(0);
                    player.killInBigList(tar5);;
                    if (pee.getClass().equals(detective.getClass())){
                        detective.removePlayerByCode(tar5);
                    }if (pee.getClass().equals(healer.getClass())){
                        healer.removePlayerByCode(tar5);
                    }if (pee.getClass().equals(commoner.getClass())){
                        commoner.removePlayerByCode(tar5);
                    }if (pee.getClass().equals(mafia.getClass())){
                        mafia.removePlayerByCode(tar5);
                    }
                }

            }
            //HEALER USER
            else if (user.getRole().getClass().equals(new Healer().getClass())) {

                if (mafia.getArraySize() > 0) {//mafia present
                    int tar0 = mafia.chooseRandom();
                    System.out.println("Mafias have chosen their target");

                    if (detective.getArraySize() > 0) {//mafia yes detective yes
                        int tar = detective.chooseRandom();
                        System.out.println("Detectives have chosen a player to test.");

                        if (player.getAlive().get(user.getIndex()) == 1) {//mafia yes detective yes user yes
                            System.out.println("Choose a player to heal: ");
                            int tar2 = m.nextInt() - 1;//target player num chosen by healer
                            while (player.getAlive().get(tar2) != 1) {
                                System.out.println("Choose a player to heal: ");
                                tar2 = m.nextInt() - 1;
                            }
                            Player pp = player.getRole(tar2);
                            pp.increaseHP(500);
                        } else if (healer.getArraySize() > 0) {//mafia yes detective yes other healer yes
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        } else {//mafia yes det yes healer no
                            System.out.println("Healers have chosen someone to heal.");
                            if (initialAlive>player.getNoOfAlive()){
                                System.out.println("Player"+(tar0+1)+" has died");
                            }else{
                                System.out.println("No one died");
                            }
                        }

                        if (player.getRole(tar).getClass().equals(mafia.getClass())) {
                            System.out.println("Player" + (tar + 1) + " has been voted out.");
                            Player pee = player.getRole(tar);
                            pee.setHP(0);
                            player.killInBigList(tar);
                            ;
                            if (pee.getClass().equals(detective.getClass())) {
                                detective.removePlayerByCode(tar);
                            }
                            if (pee.getClass().equals(healer.getClass())) {
                                healer.removePlayerByCode(tar);
                            }
                            if (pee.getClass().equals(commoner.getClass())) {
                                commoner.removePlayerByCode(tar);
                            }
                            if (pee.getClass().equals(mafia.getClass())) {
                                mafia.removePlayerByCode(tar);
                            }
                        } else {
                            if (player.getAlive().get(user.getIndex()) == 1) {
                                System.out.println("Select a person to vote out: ");
                                int tar4 = m.nextInt() - 1;//target player num chosen by commoner
                                while (player.getAlive().get(tar4) != 1) {
                                    System.out.println("Select a person to vote out: ");
                                    tar4 = m.nextInt() - 1;
                                }
                                int tar5 = commoner.chooseRandom();
                                System.out.println("Player" + (tar5 + 1) + " has been voted out");
                                Player pee = player.getRole(tar5);
                                pee.setHP(0);
                                player.killInBigList(tar5);
                                ;
                                if (pee.getClass().equals(detective.getClass())) {
                                    detective.removePlayerByCode(tar5);
                                }
                                if (pee.getClass().equals(healer.getClass())) {
                                    healer.removePlayerByCode(tar5);
                                }
                                if (pee.getClass().equals(commoner.getClass())) {
                                    commoner.removePlayerByCode(tar5);
                                }
                                if (pee.getClass().equals(mafia.getClass())) {
                                    mafia.removePlayerByCode(tar5);
                                }
                            } else {
                                int tar5 = commoner.chooseRandom();
                                System.out.println("Player" + (tar5 + 1) + " has been voted out");
                                Player pee = player.getRole(tar5);
                                pee.setHP(0);
                                player.killInBigList(tar5);
                                ;
                                if (pee.getClass().equals(detective.getClass())) {
                                    detective.removePlayerByCode(tar5);
                                }
                                if (pee.getClass().equals(healer.getClass())) {
                                    healer.removePlayerByCode(tar5);
                                }
                                if (pee.getClass().equals(commoner.getClass())) {
                                    commoner.removePlayerByCode(tar5);
                                }
                                if (pee.getClass().equals(mafia.getClass())) {
                                    mafia.removePlayerByCode(tar5);
                                }
                            }
                        }

                    } else {//mafia yes detective no
                        System.out.println("Detectives have chosen a player to test.");

                        if (player.getAlive().get(user.getIndex()) == 1) {//mafia yes detective yes user yes
                            System.out.println("Choose a player to heal: ");
                            int tar2 = m.nextInt() - 1;//target player num chosen by healer
                            while (player.getAlive().get(tar2) != 1) {
                                System.out.println("Choose a player to heal: ");
                                tar2 = m.nextInt() - 1;
                            }
                            Player pp = player.getRole(tar2);
                            pp.increaseHP(500);
                        } else if (healer.getArraySize() > 0) {//mafia yes detective yes other healer yes
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        } else {//mafia yes det yes healer no
                            System.out.println("Healers have chosen someone to heal.");
                            if (initialAlive>player.getNoOfAlive()){
                                System.out.println("Player"+(tar0+1)+" has died");
                            }else{
                                System.out.println("No one died");
                            }
                        }

                        if (player.getAlive().get(user.getIndex()) == 1) {
                            System.out.println("Select a person to vote out: ");
                            int tar4 = m.nextInt() - 1;//target player num chosen by commoner
                            while (player.getAlive().get(tar4) != 1) {
                                System.out.println("Select a person to vote out: ");
                                tar4 = m.nextInt() - 1;
                            }
                            int tar5 = commoner.chooseRandom();
                            System.out.println("Player" + (tar5 + 1) + " has been voted out");
                            Player pee = player.getRole(tar5);
                            pee.setHP(0);
                            player.killInBigList(tar5);
                            ;
                            if (pee.getClass().equals(detective.getClass())) {
                                detective.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(healer.getClass())) {
                                healer.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(commoner.getClass())) {
                                commoner.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(mafia.getClass())) {
                                mafia.removePlayerByCode(tar5);
                            }
                        } else {
                            int tar5 = commoner.chooseRandom();
                            System.out.println("Player" + (tar5 + 1) + " has been voted out");
                            Player pee = player.getRole(tar5);
                            pee.setHP(0);
                            player.killInBigList(tar5);
                            ;
                            if (pee.getClass().equals(detective.getClass())) {
                                detective.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(healer.getClass())) {
                                healer.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(commoner.getClass())) {
                                commoner.removePlayerByCode(tar5);
                            }
                            if (pee.getClass().equals(mafia.getClass())) {
                                mafia.removePlayerByCode(tar5);
                            }
                        }


                    }

                }
            }
            //COMMONER USER
            else if (user.getRole().getClass().equals(new Commoner().getClass())){

                if (mafia.getArraySize()>0){//mafia present
                    int tar0 = mafia.chooseRandom();
                    System.out.println("Mafias have chosen their target");

                    if (detective.getArraySize()>0){//mafia yes detective yes
                        int tar = detective.chooseRandom();
                        System.out.println("Detectives have chosen a player to test.");

                        if (healer.getArraySize()>0) {
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        }
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        if (player.getAlive().get(user.getIndex())==1){
                            if (player.getRole(tar).getClass().equals(mafia.getClass())){
                                System.out.println("Player"+(tar+1)+" has been voted out.");
                                Player pee = player.getRole(tar);
                                pee.setHP(0);
                                player.killInBigList(tar);;
                                if (pee.getClass().equals(detective.getClass())){
                                    detective.removePlayerByCode(tar);
                                }if (pee.getClass().equals(healer.getClass())){
                                    healer.removePlayerByCode(tar);
                                }if (pee.getClass().equals(commoner.getClass())){
                                    commoner.removePlayerByCode(tar);
                                }if (pee.getClass().equals(mafia.getClass())){
                                    mafia.removePlayerByCode(tar);
                                }
                            }
                            else{
                                System.out.println("Select a person to vote out: ");
                                int tar4 = m.nextInt()-1;//target player num chosen by commoner
                                while (player.getAlive().get(tar4)!=1){
                                    System.out.println("Select a person to vote out: ");
                                    tar4 = m.nextInt() -1;
                                }
                                int tar5 = commoner.chooseRandom();
                                System.out.println("Player"+(tar5 +1)+" has been voted out");
                                Player pee = player.getRole(tar5);
                                pee.setHP(0);
                                player.killInBigList(tar5);;
                                if (pee.getClass().equals(detective.getClass())){
                                    detective.removePlayerByCode(tar5);
                                }if (pee.getClass().equals(healer.getClass())){
                                    healer.removePlayerByCode(tar5);
                                }if (pee.getClass().equals(commoner.getClass())){
                                    commoner.removePlayerByCode(tar5);
                                }if (pee.getClass().equals(mafia.getClass())){
                                    mafia.removePlayerByCode(tar5);
                                }
                            }
                        }
                        else{
                            if (player.getRole(tar).getClass().equals(mafia.getClass())){
                                System.out.println("Player"+(tar+1)+" has been voted out.");
                                Player pee = player.getRole(tar);
                                pee.setHP(0);
                                player.killInBigList(tar);;
                                if (pee.getClass().equals(detective.getClass())){
                                    detective.removePlayerByCode(tar);
                                }if (pee.getClass().equals(healer.getClass())){
                                    healer.removePlayerByCode(tar);
                                }if (pee.getClass().equals(commoner.getClass())){
                                    commoner.removePlayerByCode(tar);
                                }if (pee.getClass().equals(mafia.getClass())){
                                    mafia.removePlayerByCode(tar);
                                }
                            }
                            else{
                                int tar5 = commoner.chooseRandom();
                                System.out.println("Player"+(tar5 +1)+" has been voted out");
                                Player pee = player.getRole(tar5);
                                pee.setHP(0);
                                player.killInBigList(tar5);;
                                if (pee.getClass().equals(detective.getClass())){
                                    detective.removePlayerByCode(tar5);
                                }if (pee.getClass().equals(healer.getClass())){
                                    healer.removePlayerByCode(tar5);
                                }if (pee.getClass().equals(commoner.getClass())){
                                    commoner.removePlayerByCode(tar5);
                                }if (pee.getClass().equals(mafia.getClass())){
                                    mafia.removePlayerByCode(tar5);
                                }
                            }
                        }


                    }else{//mafia yes detective no
                        System.out.println("Detectives have chosen a player to test.");

                        if (healer.getArraySize()>0) {
                            int tar3 = healer.chooseRandom();
                            Player pp = player.getRole(tar3);
                            pp.increaseHP(500);
                        }
                        System.out.println("Healers have chosen someone to heal.");
                        if (initialAlive>player.getNoOfAlive()){
                            System.out.println("Player"+(tar0+1)+" has died");
                        }else{
                            System.out.println("No one died");
                        }

                        if (player.getAlive().get(user.getIndex())==1){
                            System.out.println("Select a person to vote out: ");
                            int tar4 = m.nextInt()-1;//target player num chosen by commoner
                            while (player.getAlive().get(tar4)!=1){
                                System.out.println("Select a person to vote out: ");
                                tar4 = m.nextInt() -1;
                            }
                            int tar5 = commoner.chooseRandom();
                            System.out.println("Player"+(tar5 +1)+" has been voted out");
                            Player pee = player.getRole(tar5);
                            pee.setHP(0);
                            player.killInBigList(tar5);;
                            if (pee.getClass().equals(detective.getClass())){
                                detective.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(healer.getClass())){
                                healer.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(commoner.getClass())){
                                commoner.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(mafia.getClass())){
                                mafia.removePlayerByCode(tar5);
                            }
                        }
                        else{
                            int tar5 = commoner.chooseRandom();
                            System.out.println("Player"+(tar5 +1)+" has been voted out");
                            Player pee = player.getRole(tar5);
                            pee.setHP(0);
                            player.killInBigList(tar5);;
                            if (pee.getClass().equals(detective.getClass())){
                                detective.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(healer.getClass())){
                                healer.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(commoner.getClass())){
                                commoner.removePlayerByCode(tar5);
                            }if (pee.getClass().equals(mafia.getClass())){
                                mafia.removePlayerByCode(tar5);
                            }
                        }

                    }
                }

            }
            System.out.println("--End of round--");
        }
    }
}

class User{
    private static Roles<Player> role = new Roles<Player>();
    private int index;

    public void setIndex(int i){
        index =i;
    }
    public int getIndex(){
        return index;
    }

    public void setRole(Player t){
        role.setUser(t);
    }
    public Player getRole(){
        return role.getUser();
    }

}

class Roles<T>{
    private ArrayList<T> role;
    private T user;

    public void setUser(T o){
        user = o;
    }
    public T getUser(){
        return user;
    }

    public Roles(){
        role = new ArrayList<T>();
    }
    public void add(T o){
        role.add(o);
    }
    public T get(int i){
        return role.get(i);
    }
    public int size(){
        return role.size();
    }
    public void remove(int i){
        role.remove(i);
    }
    public void set(int i, T o){
        role.set(i,o);
    }

}

class Player{
    private static Roles<Player> rrr = new Roles<Player>();
    private static Roles<Integer> alive = new Roles<Integer>();
    private static int noOfAlive;
    private double playerHP;

    public void addRole(Player t){
        rrr.add(t);
        alive.add(1);
        noOfAlive++;
    }
    public Player getRole(int i){
        return rrr.get(i);
    }

    public int getNoOfAlive(){
        return noOfAlive;
    }
    public Roles<Integer> getAlive(){
        return alive;
    }
    public void increaseHP(double i){
        playerHP+=i;
    }
    public double getPlayerHP(){
        return playerHP;
    }
    public void reduceHP(double i){
        playerHP-=i;
    }
    public void setHP(double i){
        playerHP=i;
    }
    public void killInBigList(int i){
        alive.set(i,0);
        noOfAlive--;
    }


}

class Mafia extends Player implements choosePlayer{
    private  static Roles<Integer> mff = new Roles<Integer>();
    private double playerHP=2500;

    public void addRoles(int t){
        mff.add(t);
    }
    public int getRoles(int i){
        return mff.get(i);
    }
    public int getArraySize(){
        return mff.size();
    }

    @Override
    public void increaseHP(double i) {
        super.increaseHP(i);
        playerHP+=i;
    }

    @Override
    public void reduceHP(double i) {
        super.reduceHP(i);
        playerHP-=i;
    }

    @Override
    public double getPlayerHP() {
        return playerHP;
    }

    @Override
    public void setHP(double i) {
        super.setHP(i);
        playerHP =i;
    }

    public static void sortbyColumn(double arr[][], int col) {
        Arrays.sort(arr, new Comparator<double[]>() {

            @Override
            public int compare(final double[] entry1, final double[] entry2) {
                if (entry1[col] > entry2[col])
                    return 1;
                else
                    return -1;
            }
        });
    }

    public void killHP(Player p,int tarrr){
        double mafiaHp=0;
        Player player = new Player();
        for (int i=0;i<mff.size();i++){
            mafiaHp+=player.getRole(mff.get(i)).getPlayerHP();
        }
        if (mafiaHp==p.getPlayerHP()){
            p.setHP(0);
            player.killInBigList(tarrr);
            Detective detective = new Detective();
            Commoner commoner = new Commoner();
            Healer healer= new Healer();
            if (p.getClass().equals(detective.getClass())){
                detective.removePlayerByCode(tarrr);
            }if (p.getClass().equals(healer.getClass())){
                healer.removePlayerByCode(tarrr);
            }if (p.getClass().equals(commoner.getClass())){
                commoner.removePlayerByCode(tarrr);
            }
        }
        else if (mafiaHp<p.getPlayerHP()){
            p.reduceHP(mafiaHp);
            for (int i=0;i<mff.size();i++){
                player.getRole(mff.get(i)).setHP(0);
            }
        }else{
            double n=p.getPlayerHP();
            p.setHP(0);
            int a= mff.size();
            double[][] array = new double[a][2];//playerhp,index in mff
            for (int i=0;i<a;i++){
                array[i][0]= player.getRole(mff.get(i)).getPlayerHP();
                array[i][1]=i;
            }
            sortbyColumn(array,0);
            for (int i=0;i<mff.size();i++){
                Mafia currMafia = (Mafia) player.getRole(mff.get((int) array[i][1]));
                double x = n/a;
                double y= currMafia.getPlayerHP();
                a--;
                if (y<=x){
                    currMafia.setHP(0);
                    n=n-y;
                }else{
                    currMafia.setHP(y-x);
                    n=n-x;
                }
            }
            player.killInBigList(tarrr);
            Detective detective = new Detective();
            Commoner commoner = new Commoner();
            Healer healer= new Healer();
            if (p.getClass().equals(detective.getClass())){
                detective.removePlayerByCode(tarrr);
            }if (p.getClass().equals(healer.getClass())){
                healer.removePlayerByCode(tarrr);
            }if (p.getClass().equals(commoner.getClass())){
                commoner.removePlayerByCode(tarrr);
            }
        }
    }

    public void removePlayerByCode(int x){
        for (int i=0;i<mff.size();i++){
            if (mff.get(i)==x){
                mff.remove(i);
                break;
            }
        }
    }
    public void removePlayerByIndex(int x){
        mff.remove(x);
    }

    private int chooseAtRandom(){
        Random rand = new Random();
        int tar = rand.nextInt(10);
        Player player = new Player();
        while (player.getAlive().get(tar)!=1 || player.getRole(tar).getClass().equals(getClass())){
            tar = rand.nextInt(10);
        }
        Player killPlayer = player.getRole(tar);
        killHP(killPlayer,tar);
        return tar;
    }

    @Override
    public int chooseRandom() {
        this.chooseAtRandom();
        return 0;
    }
}

class Commoner extends Player implements choosePlayer{
    private  static Roles<Integer> mff = new Roles<Integer>();
    private double playerHP=1000;

    public void addRoles(int t){
        mff.add(t);
    }
    public int getRoles(int i){
        return mff.get(i);
    }
    public int getArraySize(){
        return mff.size();
    }
    public void removePlayerByCode(int x){
        for (int i=0;i<mff.size();i++){
            if (mff.get(i)==x){
                mff.remove(i);
                break;
            }
        }
    }
    public void removePlayerByIndex(int x){
        mff.remove(x);
    }
    @Override
    public void increaseHP(double i) {
        super.increaseHP(i);
        playerHP+=i;
    }

    @Override
    public void reduceHP(double i) {
        super.reduceHP(i);
        playerHP-=i;
    }

    @Override
    public double getPlayerHP() {
        return playerHP;
    }

    @Override
    public void setHP(double i) {
        super.setHP(i);
        playerHP =i;
    }

    private int chooseAtRandom(){
        Random rand = new Random();
        Player player = new Player();
        int tar = rand.nextInt(player.getAlive().size());
        while (player.getAlive().get(tar)==0){
            tar = rand.nextInt(player.getAlive().size());
        }
        return tar;
    }

    @Override
    public int chooseRandom() {
        return this.chooseAtRandom();
    }
}

class Detective extends Player implements choosePlayer{
    private  static Roles<Integer> mff = new Roles<Integer>();
    private double playerHP=800;

    public void addRoles(int t){
        mff.add(t);
    }
    public int getRoles(int i){
        return mff.get(i);
    }
    public int getArraySize(){
        return mff.size();
    }
    public void removePlayerByCode(int x){
        for (int i=0;i<mff.size();i++){
            if (mff.get(i)==x){
                mff.remove(i);
                break;
            }
        }
    }
    public void removePlayerByIndex(int x){
        mff.remove(x);
    }
    @Override
    public void increaseHP(double i) {
        super.increaseHP(i);
        playerHP+=i;
    }

    @Override
    public void reduceHP(double i) {
        super.reduceHP(i);
        playerHP-=i;
    }

    @Override
    public void setHP(double i) {
        super.setHP(i);
        playerHP =i;
    }

    @Override
    public double getPlayerHP() {
        return playerHP;
    }

    private int chooseatRandom(){
        Random rand = new Random();
        Player player = new Player();
        int tar = rand.nextInt(player.getAlive().size()); //target player num chosen by detective
        while (player.getAlive().get(tar)!=1 || player.getRole(tar).getClass().equals(getClass())){
            tar = rand.nextInt(player.getAlive().size());
        }
        return tar;
    }

    @Override
    public int chooseRandom() {
        return this.chooseatRandom();
    }
}

class Healer extends Player implements choosePlayer{
    private  static Roles<Integer> mff = new Roles<Integer>();
    private double playerHP=800;

    public void addRoles(int t){
        mff.add(t);
    }
    public int getRoles(int i){
        return mff.get(i);
    }
    public int getArraySize(){
        return mff.size();
    }
    @Override
    public void increaseHP(double i) {
        super.increaseHP(i);
        playerHP+=i;
    }

    @Override
    public void reduceHP(double i) {
        super.reduceHP(i);
        playerHP-=i;
    }

    @Override
    public void setHP(double i) {
        super.setHP(i);
        playerHP =i;
    }

    @Override
    public double getPlayerHP() {
        return playerHP;
    }
    public void removePlayerByCode(int x){
        for (int i=0;i<mff.size();i++){
            if (mff.get(i)==x){
                mff.remove(i);
                break;
            }
        }
    }
    public void removePlayerByIndex(int x){
        mff.remove(x);
    }
    private int chooseAtRandom(){
        Random rand = new Random();
        Player player = new Player();
        int tar2 = rand.nextInt(player.getAlive().size());//target player num chosen by healer
        while (player.getAlive().get(tar2)!=1){
            tar2 = rand.nextInt(player.getAlive().size());
        }
        return tar2;
    }

    @Override
    public int chooseRandom() {
        return this.chooseAtRandom();
    }
}


