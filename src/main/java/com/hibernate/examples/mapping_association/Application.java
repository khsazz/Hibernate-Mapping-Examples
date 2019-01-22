package com.hibernate.examples.mapping_association;

import com.hibernate.examples.mapping_association.entity.Account;
import com.hibernate.examples.mapping_association.entity.Transaction;
import com.hibernate.examples.mapping_association.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Sazzad on 21-Apr-16 [5:37 PM].
 */


public class Application {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args){

        init();

//        experiment_Unidirectional_OneToMany();
//        experiment_BiDirectional_OneToMany();
//        experiment_BiDirectional_ManyToMany();
        experiment_Unidirectional_ManyToMany();

        terminate();

    }

    private static void init(){
        emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    private static void terminate(){
        em.close();
        emf.close();
    }

    private static void experiment_Unidirectional_OneToMany(){
        Account account1 = new Account();
//        Account account1 = em.find(Account.class, 9);
        account1.setAccountName("Savings");
        account1.setLocation("banani");

        Transaction transaction = new Transaction();
        transaction.setTransactionType("Remitance");
        transaction.setAmount(10000);

        account1.getTransactionList().add(transaction);

        em.persist(account1);
        em.getTransaction().commit();
        em.refresh(account1);

        for(Transaction t : account1.getTransactionList()){
            System.out.println(t.getAmount());
        }
    }

    private static void experiment_BiDirectional_OneToMany(){
        Account account1 = new Account();
        account1.setAccountName("Current");
        account1.setLocation("banani");

        Transaction transaction1 = new Transaction();
        transaction1.setAmount(111);
        transaction1.setTransactionType("Debit");
        transaction1.setAccount(account1);

        account1.getTransactionList().add(transaction1);

        em.persist(account1);
        em.getTransaction().commit();
        em.refresh(account1);

        for(Transaction t : account1.getTransactionList()){
            System.out.println(t.getAmount());
        }
    }

    private static void experiment_Unidirectional_ManyToMany(){

        Account account1 = new Account();
        account1.setAccountName("Business");
        account1.setLocation("Nepal");

        Account account2 = new Account();
        account2.setAccountName("Loan");
        account2.setLocation("Nepal");

        User user1 = new User();
        user1.setUserName("rock");

        User user2 = new User();
        user2.setUserName("hhh");

        user1.getAccountList().add(account1);
        user1.getAccountList().add(account2);

        user2.getAccountList().add(account1);
        user2.getAccountList().add(account2);

        em.persist(user1);
        em.persist(user2);
        em.getTransaction().commit();
    }

//    private static void experiment_BiDirectional_ManyToMany(){
//
//        Account account1 = em.find(Account.class, 23), account2 = em.find(Account.class, 18);
//        User user1 = em.find(User.class, 13), user2 = em.find(User.class, 14);
//
//        user1.getAccountList().add(account1);
//        user1.getAccountList().add(account2);
//
//        user2.getAccountList().add(account1);
//        user2.getAccountList().add(account2);
//
//        em.persist(user1);
//        em.persist(user2);
//        em.getTransaction().commit();
//
//        User user = em.find(User.class, 13);
//
//        System.out.println("id : "+user.getUserId());
//        System.out.println("name : "+user.getUserName());
//        System.out.println("accounts : ");
//        for(Account a : user.getAccountList()){
//            System.out.println(a.getAccountId() +" - "+a.getAccountName());
//        }
//
//        Account account = em.find(Account.class, 23);
//        System.out.println("user : ");
//        for(User u : account.getUserList()){
//            System.out.println(u.getUserName());
//        }
//    }
}
