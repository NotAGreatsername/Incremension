package com.sfanshen.currency;

import com.sfanshen.main.BigNum;
import com.sfanshen.graphics.Picture;
import com.sfanshen.upgrade.BoostUpgrade;

import java.util.ArrayList;


public class Currency{
  public String name;
  public BigNum amount;
  //Additive boosts are applied before multiplicative ones in the form of baseBoost and finalBoost
  BigNum finalBoost;
  BigNum additiveBoost;
  BigNum multiplicativeBoost;
  BigNum exponentialBoost;
  
  public ArrayList<BoostUpgrade> additiveUpgrades;
  public ArrayList<BoostUpgrade> multiplicativeUpgrades;
  ArrayList<BoostUpgrade> exponentialUpgrades;
  
  public Picture icon;
  
  public Currency(String name, Picture icon){
    this.name = name;
    this.amount = new BigNum(0);
    additiveUpgrades = new ArrayList<>();
    multiplicativeUpgrades = new ArrayList<>();
    exponentialUpgrades = new ArrayList<>();
    
    this.finalBoost = new BigNum(1);
    this.additiveBoost = new BigNum(1);
    this.multiplicativeBoost = new BigNum(1);
    this.exponentialBoost = new BigNum(1);
    
    this.icon = icon;
  }
  
  public void set(BigNum amount){
    this.amount.set(amount);
  }
  
  public void increase(BigNum n){
    n.multiply(this.finalBoost);
    this.amount.add(n);
  }
  public void increase(int n){
    this.amount.add(BigNum.multiply(this.finalBoost, new BigNum(n)));
  }
  public void increase(){
    this.amount.add(this.finalBoost);
  }
  
  
  public void calculateBoost(){
    this.finalBoost.set(1);
    
    this.finalBoost.add(this.additiveBoost);
    this.finalBoost.multiply(this.multiplicativeBoost);
    this.finalBoost.pow(this.exponentialBoost);
  }
  
  public void calculateAdditiveBoost(){
     for (BoostUpgrade additiveUpgrade : this.additiveUpgrades){
       if (additiveUpgrade.level > 0){
         this.additiveBoost.add(additiveUpgrade.calculateBoost(this));
       }
    }
  }
  
  public void calculateMultiplicativeBoost(){
    for (BoostUpgrade multiplicativeUpgrade: multiplicativeUpgrades){
      if (multiplicativeUpgrade.level > 0){
        multiplicativeBoost.multiply(multiplicativeUpgrade.calculateBoost(this));
      }
    }
  }
  
  public void calculateExponentialBoost(){
    for (BoostUpgrade exponentialUpgrade: exponentialUpgrades){
      if (exponentialUpgrade.level > 0){
        exponentialBoost.multiply(exponentialUpgrade.calculateBoost(this));
      
      }
    }
  }
}