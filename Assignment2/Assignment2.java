import java.util.ArrayList;
import java.util.Scanner;

public class Assignment2 {

    public static void main(String args[]){

        //restaurant rest = new restaurant();
        Zotato menu = new Zotato();
        menu.insert("Shah","Authentic");
        menu.insert("Ravi's","");
        menu.insert("The Chinease","Authentic");
        menu.insert("Wang's","Fast Food");
        menu.insert("Paradise","");

        Elite obj1 = new Elite();
        obj1.setName("Ram");
        Elite obj2 = new Elite();
        obj1.setType("Elite");
        obj2.setName("Sam");
        obj2.setType("Elite");
        obj1.setAdditionalDisc(50);
        obj1.setDeliveryFee(0);
        obj2.setAdditionalDisc(50);
        obj2.setDeliveryFee(0);
        obj1.setCity("Pune");
        obj2.setCity("Delhi");
        Special obj3=new Special();
        obj3.setName("Tim");
        obj3.setType("Special");
        obj3.setAdditionalDisc(25);
        obj3.setDeliveryFee(20);
        obj3.setCity("Mumbai");
        Normal obj4 = new Normal();
        Normal obj5 = new Normal();
        obj4.setName("Kim");
        obj5.setName("Jim");
        obj4.setType("Normal");
        obj5.setType("Normal");
        obj4.setAdditionalDisc(0);
        obj4.setDeliveryFee(40);
        obj5.setAdditionalDisc(0);
        obj5.setDeliveryFee(40);
        obj4.setCity("Hyderabad");
        obj5.setCity("Kolkata");
        customerDatabase cDb = new customerDatabase();
        cDb.addConsumer(obj1);
        cDb.addConsumer(obj2);
        cDb.addConsumer(obj3);
        cDb.addConsumer(obj4);
        cDb.addConsumer(obj5);


        while (menu.getstatus()==1) {
            menu.display();
            Scanner m = new Scanner(System.in);
            int query = m.nextInt();
            if (query==1){
                menu.f1();

            }

            if (query==2){
                menu.f2();
            }
            if (query==3){
                menu.f3();
            }

            if (query==4){
                menu.f4();
            }
            if (query==5){
                menu.f5();
            }
        }

    }

}

class restaurantDatabase{
    private static ArrayList<restaurant> restaurants = new ArrayList<restaurant>();

    public void addrest(restaurant obj){
        restaurants.add(obj);
    }

    public ArrayList<restaurant> getRestaurants(){
        return restaurants;

    }
}

class customerDatabase{
    private  static ArrayList<consumer> consumers = new ArrayList<consumer>();
    public void addConsumer(consumer c){
        consumers.add(c);
    }

    public ArrayList<consumer> getConsumers(){
        return consumers;
    }
}

class Zotato implements  work{

    private int active=1;
    private static int currCust=0;
    private static int currRest=0;
    private static double charges=0;
    private static double deliveryCharges=0;

    public void addDelCharges(double x){
        deliveryCharges+=x;
    }

    public void addCharges(double x){
        charges+=x;
    }
    private void getCharges(){
        System.out.println("Total Company balance - INR "+charges+"/-");
        System.out.println("Total Delivery Charges Collected - INR "+deliveryCharges+"/-");

    }

    public void insert(String name, String t){
        restaurant obj = new restaurant();
        obj.setNameType(name,t);
        restaurantDatabase rd = new restaurantDatabase();
        rd.addrest(obj);
    }


    public int getCurrCust(){
        return currCust;
    }
    public int getCurrRest(){
        return  currRest;
    }

    private void print(){
        System.out.println("Welcome to Zotato:");
        System.out.println("1) Enter as Restaurant Owner");
        System.out.println("2) Enter as Customer");
        System.out.println("3) Check User Details");
        System.out.println("4) Company Account details");
        System.out.println("5) Exit");
    }

    private void asRestaurant(){
        restaurant rest = new restaurant();
        rest.display();
        Scanner m = new Scanner(System.in);
        int query = m.nextInt();
        currRest=query-1;
        while(rest.getstatus()==1) {

            rest.print2(query-1);
            int query2 = m.nextInt();
            if (query2==1){
                rest.f1();
                rest.setOwner(query-1);
            }
            if (query2==2){
                rest.f2();
                rest.edit2(query-1);
            }
            if (query2==3){
                rest.f3();
            }
            if (query2==4){
                rest.f4();
                rest.restDisc2(query-1);
            }
            if (query2==5){
                rest.f5();
            }
        }

    }

    private void asCustomer(){
        consumer cons = new consumer();
        customerDatabase cDb = new customerDatabase();
        ArrayList<consumer> obj =cDb.getConsumers();
        for (int i=0;i<obj.size();i++){
            if (obj.get(i).getType().equals("Normal")){
                //System.out.println(obj.get(i).getClass());
                System.out.println((i+1) + ". " + obj.get(i).getName());
            }else {
                System.out.println((i+1) + ". " + obj.get(i).getName() + " (" + obj.get(i).getType() + ")");
            }
        }
        Scanner m = new Scanner(System.in);
        int q1 = m.nextInt();
        currCust=q1-1;

        while (cons.getStatus()==1) {
            System.out.println("Welcome " + obj.get(q1 - 1).getName());
            cons.display();

            int q2 = m.nextInt();
            if (q2 == 1) {
                cons.f1();
            }
            if (q2 == 2) {
                cons.f2();
            }
            if (q2 == 3) {
                cons.f3();
            }
            if (q2 == 4) {
                cons.f4();
            }
            if (q2 == 5) {
                cons.f5();
            }
        }

    }

    private void userDetails(){
        System.out.println("1) Customer List");
        System.out.println("2) Restaurant List");
        Scanner m = new Scanner(System.in);
        int q1 = m.nextInt();
        restaurantDatabase rDb = new restaurantDatabase();
        ArrayList<restaurant> restaurants = rDb.getRestaurants();
        customerDatabase cDb = new customerDatabase();
        ArrayList<consumer> consumers = cDb.getConsumers();
        if (q1==1){
            for (int i=0;i<consumers.size();i++){
                System.out.println((i+1)+". "+ consumers.get(i).getName());
            }
            int q2 = m.nextInt()-1;
            consumer curr = consumers.get(q2);
            System.out.println(curr.getName()+"("+curr.getType()+") , "+curr.getCity()+", "+ curr.getWallet()+"/-");
        }else{
            for (int i=0;i<restaurants.size();i++){
                System.out.println((i+1)+". "+restaurants.get(i).getName());
            }
            int q2 = m.nextInt()-1;
            restaurant curr = restaurants.get(q2);
            System.out.println(curr.getName()+"("+curr.getType()+") , "+ curr.getReward());
        }
    }

    private void exit(){
        active=0;
    }

    public int getstatus(){
        return active;
    }


    @Override
    public void display() {
        this.print();
    }

    @Override
    public void f1() {
        this.asRestaurant();
    }

    @Override
    public void f2() {

        this.asCustomer();

    }

    @Override
    public void f3() {

        this.userDetails();
    }

    @Override
    public void f4() {

        this.getCharges();
    }

    @Override
    public void f5() {
        this.exit();
    }
}

class Dish{
    private String name;
    private int price;
    private int quantity;
    private String category;
    private int id;
    private int offer;
    private String rest;
    private static int number=0;

    public Dish(String n,int p, int q, String c,int o){
        this.name=n;
        this.price=p;
        this.quantity=q;
        this.category=c;
        this.offer=o;
        number++;
        this.id=number;
    }

    public void setRest(String qw){
        rest=qw;
    }
    public String getRest(){
        return rest;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getCategory(){
        return category;
    }
    public int getOffer(){
        return offer;
    }
    public void setName(String s){
        name=s;
    }
    public void setPrice(int s){
        price=s;
    }
    public void setQuantity(int s){
        quantity=s;
    }
    public void setCategory(String s){
        category=s;
    }
    public void setOffer(int s){
        offer=s;
    }

}

class restaurant implements work{

    private int active=1;
    private static ArrayList<Dish> dishes = new ArrayList<Dish>();
    private ArrayList<Dish> dishes2 = new ArrayList<Dish>();
    private static ArrayList<Integer> restRewards = new ArrayList<Integer>();

    private String name;
    private String type;
    private int restDisc;
    private int[] reward = new int[2];

    public void setNameType(String n, String t){
        name=n;
        type=t;
        setReward();
        restRewards.add(new Integer(0));
    }
    private void setReward(){
        if (type.equals("Fast Food")) {
            reward[0] =10;
            reward[1]=150;
        }
        else if (type.equals("Authentic")) {
            reward[0] =25;
            reward[1]=200;
        }
        else{
            reward[0] =5;
            reward[1]=100;
        }
    }

    public void setRestReward(int x, int us){
        restRewards.set(us,restRewards.get(us)+x);
    }

    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }

    public int[] getReward(){
        return reward;
    }
    public ArrayList<Dish> getDishes(){
        return dishes;
    }

    public void setRestDisc(int x){
        restDisc=x;
    }

    public int getRestDisc(){
        return  restDisc;
    }

    public void addDish(Dish d){
        dishes2.add(d);
    }


    public int getstatus(){
        return active;
    }

    public void print2(int s){
        restaurantDatabase rd = new restaurantDatabase();
        ArrayList<restaurant> obj= rd.getRestaurants();
        System.out.println("Welcome "+ obj.get(s).name);
        System.out.println("1) Add item");
        System.out.println("2) Edit item");
        System.out.println("3) Print Rewards");
        System.out.println("4) Discount on bill value");
        System.out.println("5) Exit");

    }

    private void print(){
        System.out.println("Choose Restaurant");
        restaurantDatabase rd = new restaurantDatabase();
        ArrayList<restaurant> obj= rd.getRestaurants();
        for (int i=0;i<obj.size();i++){
            if (obj.get(i).type.equals("")){
                System.out.println((i+1)+") "+obj.get(i).name);
            }else {
                System.out.println((i+1)+") "+obj.get(i).name + " (" + obj.get(i).type + ")");
            }
        }
    }

    private void addItem(){
        Scanner m = new Scanner(System.in);
        System.out.println("Enter food item details \n"+"Food name");
        String n = m.nextLine();
        System.out.println("item price");
        int p=m.nextInt();
        System.out.println("item quantity");
        int q=m.nextInt();
        System.out.println("item category");
        m.nextLine();
        String c=m.nextLine();
        System.out.println("offer");
        int off= m.nextInt();
        Dish obj = new Dish(n,p,q,c,off);
        dishes.add(obj);
        addDish(obj);
        System.out.println(obj.getId() +" "+obj.getName()+" "+obj.getPrice()+" "+ obj.getQuantity()+" "+ obj.getOffer()+"% off "+obj.getCategory());
    }


    public void setOwner(int q){
        restaurantDatabase rd = new restaurantDatabase();
        ArrayList<restaurant> obj= rd.getRestaurants();
        String name = obj.get(q).name;
        dishes.get(dishes.size()-1).setRest(name);
        //dishes2.get(dishes2.size()-1).setRest(name);
    }

    private void editItem(){
        System.out.println("Choose item by code");

    }

    public void edit2(int q){
        restaurantDatabase rd = new restaurantDatabase();
        ArrayList<restaurant> obj2= rd.getRestaurants();
        String name = obj2.get(q).name;
        for (int i=0;i<dishes.size();i++) {
            Dish obj = dishes.get(i);
            if (obj.getRest().equals(name)) {
                System.out.println(obj.getId() + " " + obj.getRest() + " - " + obj.getName() + " " + obj.getPrice() + " " + obj.getQuantity() + " " + obj.getOffer() + "% off " + obj.getCategory());
            }
        }
        Scanner m = new Scanner(System.in);
        int q2=m.nextInt() -1;
        System.out.println("Choose an attribute to edit:");
        System.out.println("1) Name");
        System.out.println("2) Price");
        System.out.println("3) Quantity");
        System.out.println("4) Category");
        System.out.println("5) Offer");
        int q3 = m.nextInt();
        if (q3==1){
            System.out.println("Enter the new name");
            m.nextLine();
            String c=m.nextLine();
            dishes.get(q2).setName(c);
        }
        if (q3==2){
            System.out.println("Enter the new price");
            int a=m.nextInt();
            dishes.get(q2).setPrice(a);
        }
        if (q3==3){
            System.out.println("Enter the new quantity");
            int a=m.nextInt();
            dishes.get(q2).setQuantity(a);
        }
        if (q3==4){
            System.out.println("Enter the new category");
            m.nextLine();
            String c=m.nextLine();
            dishes.get(q2).setCategory(c);
        }
        if (q3==5){
            System.out.println("Enter the new Offer");
            int a=m.nextInt();
            dishes.get(q2).setOffer(a);
        }
        Dish obj = dishes.get(q2);
        System.out.println(obj.getId() + " " + obj.getRest() + " - " + obj.getName() + " " + obj.getPrice() + " " + obj.getQuantity() + " " + obj.getOffer() + "% off " + obj.getCategory());

    }

    private void restDisc(){
        System.out.println("Enter offer on total bill value - ");

    }
    public void restDisc2(int q){
        Scanner m = new Scanner(System.in);
        int x= m.nextInt();
        restaurantDatabase rd = new restaurantDatabase();
        ArrayList<restaurant> obj2= rd.getRestaurants();
        restaurant obj = obj2.get(q);
        obj.setRestDisc(x);
    }

    private void printRewards(){
        Zotato zzz = new Zotato();
        int currRest = zzz.getCurrRest();
        System.out.println("Reward points- "+ restRewards.get(currRest));
    }

    private void exit(){
        active=0;
    }

    @Override
    public void display() {
        this.print();
    }

    @Override
    public void f1() {

        this.addItem();
    }

    @Override
    public void f2() {

        this.editItem();
    }

    @Override
    public void f3() {

        this.printRewards();

    }

    @Override
    public void f4() {

        this.restDisc();

    }

    @Override
    public void f5() {

        this.exit();
    }
}




class consumer implements work{

    private int active=1;
    private String name;
    private String type;
    private int deliveryFee;
    private int additionalDisc;
    private static int rewards;
    private static double wallet;
    private String city;
    private static ArrayList<ArrayList<Dish>> cart= new ArrayList<ArrayList<Dish>>();
    private static ArrayList<ArrayList<Dish>> prevOrders = new ArrayList<ArrayList<Dish>>();
    //private String type;

    public void addCart(Dish d, int us){
        cart.get(us).add(d);
    }
    public String getCity(){
        return city;
    }
    public void setCity(String x){
        city = x;
    }
    public double getWallet(){
        return wallet;
    }

    public ArrayList<ArrayList<Dish>> getCart(){
        return cart;
    }
    public ArrayList<ArrayList<Dish>> getPrevOrders(){
        return prevOrders;
    }

    public void setDeliveryFee(int s){
        deliveryFee=s;
    }
    public int getDeliveryFee(){
        return deliveryFee;
    }
    public void setAdditionalDisc(int s){
        additionalDisc=s;
    }
    public int getAdditionalDisc(){
        return additionalDisc;
    }

    public void setName(String s){
        name=s;
        cart.add(new ArrayList<Dish>());
        prevOrders.add(new ArrayList<Dish>());
        wallet=1000;
        rewards=0;
    }

    public void addRewards(int x){
        rewards+=x;
    }
    public void useRewards(int x){
        rewards-=x;
    }
    public int getRewards(){
        return rewards;
    }
    public void useWallet(double x){
        wallet-=x;
    }
    public String getName(){
        return name;
    }

    public void setType(String s){
        type=s;
    }

    public String getType(){
        return type;
    }

    public int getStatus(){
        return active;
    }


    public void print(){
        System.out.println("Customer Menu");
        System.out.println("1) Select Restaurant");
        System.out.println("2) checkout cart");
        System.out.println("3) Reward won");
        System.out.println("4) print the recent orders");
        System.out.println("5) Exit");
    }

    private void restNames(){
        restaurant rest = new restaurant();
        rest.display();
        Scanner m=new Scanner(System.in);
        int q = m.nextInt()-1;
        System.out.println("Choose item by code");
        restaurantDatabase rd = new restaurantDatabase();
        ArrayList<restaurant> obj2= rd.getRestaurants();
        String name = obj2.get(q).getName();
        ArrayList<Dish> dishes = rest.getDishes();
        for (int i=0;i<dishes.size();i++) {
            Dish obj = dishes.get(i);
            if (obj.getRest().equals(name)) {
                System.out.println(obj.getId() + " " + obj.getRest() + " - " + obj.getName() + " " + obj.getPrice() + " " + obj.getQuantity() + " " + obj.getOffer() + "% off " + obj.getCategory());
            }
        }

        int q2 = m.nextInt()-1;//dish id
        int quant = quantity();//item quant
        int oldQuant = dishes.get(q2).getQuantity();
        while (quant>oldQuant){
            System.out.println("Please select again");
            quant = quantity();
        }
        Zotato z = new Zotato();
        int currcust = z.getCurrCust();//cust id
        dishes.get(q2).setQuantity(oldQuant-quant);
        Dish cartDish = dishes.get(q2);
        cartDish.setQuantity(quant);
        addCart(cartDish,currcust);

        System.out.println("Items added to cart");

    }

    public int quantity(){
        Scanner m= new Scanner(System.in);
        System.out.println("Enter Quantity");
        int quant = m.nextInt();
        return quant;
    }

    private void checkoutCart(){
        Zotato z = new Zotato();
        int currcust = z.getCurrCust();//cust id
        ArrayList<ArrayList<Dish>> cart = getCart();
        System.out.println("Items in cart -");
        ArrayList<Dish> d = cart.get(currcust);
        int totalQuant=0;
        double orderTotal=0;
        restaurantDatabase rDb = new restaurantDatabase();
        ArrayList<restaurant> restaurants = rDb.getRestaurants();
        int currRest=0;
        for (int i=0;i<d.size();i++){
            Dish obj = d.get(i);
            totalQuant+=obj.getQuantity();
            double am = obj.getPrice()* obj.getQuantity();
            for (int j=0;j<restaurants.size();j++){
                if (restaurants.get(j).getName().equals(obj.getRest())){
                    currRest=j;
                    double ab = ((double)restaurants.get(j).getRestDisc()/100)*am;
                    am = am - ab;
                }
            }
            am = am - ((double)obj.getOffer()/100)*am;
            orderTotal+=am;
            System.out.println(obj.getId() + " " + obj.getRest() + " - " + obj.getName() + " " + obj.getPrice() + " " + obj.getQuantity() + " " + obj.getOffer() + "% off " + obj.getCategory());
        }
        customerDatabase cDb = new customerDatabase();
        consumer cust = cDb.getConsumers().get(currcust);
        int deliveryCharges = cust.getDeliveryFee();
        System.out.println("Delivery charges- "+deliveryCharges+"/-");
        orderTotal=orderTotal - cust.getAdditionalDisc();
        double orderWithoutDel = orderTotal;
        orderTotal +=deliveryCharges;
        System.out.println("Total order value- "+orderTotal+"/-");
        System.out.println("    1) Proceed to checkout");
        Scanner m = new Scanner(System.in);
        m.nextInt();
        int rew =cust.getRewards();
        double wallet = orderTotal-rew;
        if (wallet>0){
            cust.useRewards(cust.getRewards());
            cust.useWallet(wallet);
        }
        else {
            cust.useRewards(rew-cust.getRewards());
        }
        System.out.println(totalQuant +" items successfully bought for INR "+ orderTotal+"/-");
        ArrayList<ArrayList<Dish>> prevOrders = getPrevOrders();
        int[] rewCriteria = restaurants.get(currRest).getReward();
        int newRews = (int) (orderWithoutDel/rewCriteria[1]);
        newRews = newRews*rewCriteria[0];
        cust.addRewards(newRews);
        restaurant rrr = new restaurant();
        rrr.setRestReward(newRews,currRest);
        //restaurants.get(currcust).setReward.......
        while(cart.get(currcust).size()!=0) {
            Dish de = cart.get(currcust).get(0);
            prevOrders.get(currcust).add(de);
            cart.get(currcust).remove(0);
        }
        //restFee
        double charges = (orderWithoutDel /100);
        Zotato zzz = new Zotato();
        zzz.addCharges(charges);
        zzz.addDelCharges(deliveryCharges);
    }

    private void printRewards(){
        Zotato zzz = new Zotato();
        int currCust = zzz.getCurrCust();
        customerDatabase cDb = new customerDatabase();
        ArrayList<consumer> consumers = cDb.getConsumers();
        System.out.println("Total Rewards - "+ consumers.get(currCust).getRewards());
    }

    private void printRecentOrders(){
        Zotato zzz = new Zotato();
        int currCust = zzz.getCurrCust();
        customerDatabase cDb = new customerDatabase();
        ArrayList<consumer> consumers = cDb.getConsumers();
        ArrayList<ArrayList<Dish>> prevOrders = getPrevOrders();
        for (int i=0;i<prevOrders.get(currCust).size();i++){
            Dish d = prevOrders.get(currCust).get(i);
            System.out.println("Bought Item: "+ d.getName() + " quantity: "+d.getQuantity()+"for RS "+d.getPrice()+" from "+ d.getRest()+" and delivery charge: "+ consumers.get(currCust).getDeliveryFee());
        }
    }

    private void exit(){
        active=0;
    }

    @Override
    public void display() {

        this.print();
    }

    @Override
    public void f1() {
        this.restNames();

    }

    @Override
    public void f2() {

        this.checkoutCart();

    }

    @Override
    public void f3() {

        this.printRewards();

    }

    @Override
    public void f4() {

        this.printRecentOrders();

    }

    @Override
    public void f5() {


        this.exit();
    }

}

class Elite extends consumer{
    //private int deliveryFee;
    //private int additionalDisc;
    //private String type;


    @Override
    public void setDeliveryFee(int i) {
        super.setDeliveryFee(i);
    }

    @Override
    public int getDeliveryFee() {
        return super.getDeliveryFee();
    }

    @Override
    public void setAdditionalDisc(int i) {
        super.setAdditionalDisc(i);
    }

    @Override
    public int getAdditionalDisc() {
        return super.getAdditionalDisc();
    }

    @Override
    public void setType(String s) {
        super.setType(s);
    }

    @Override
    public String getType() {
        return super.getType();
    }
}
class Special extends consumer{
    //private int deliveryFee;
    //private int additionalDisc;
    //private String type;


    @Override
    public void setDeliveryFee(int i) {
        super.setDeliveryFee(i);
    }

    @Override
    public int getDeliveryFee() {
        return super.getDeliveryFee();
    }

    @Override
    public void setAdditionalDisc(int i) {
        super.setAdditionalDisc(i);
    }

    @Override
    public int getAdditionalDisc() {
        return super.getAdditionalDisc();
    }

    @Override
    public void setType(String s) {
        super.setType(s);
    }

    @Override
    public String getType() {
        return super.getType();
    }
}class Normal extends consumer{
    //private int deliveryFee;
    //private int additionalDisc;
    //private String type;


    @Override
    public void setDeliveryFee(int i) {
        super.setDeliveryFee(i);
    }

    @Override
    public int getDeliveryFee() {
        return super.getDeliveryFee();
    }

    @Override
    public void setAdditionalDisc(int i) {
        super.setAdditionalDisc(i);
    }

    @Override
    public int getAdditionalDisc() {
        return super.getAdditionalDisc();
    }

    @Override
    public void setType(String s) {
        super.setType(s);
    }

    @Override
    public String getType() {
        return super.getType();
    }
}
