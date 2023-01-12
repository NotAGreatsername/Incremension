

class FeatureUpgrade extends Upgrade{
  
  boolean isBought;
  
  FeatureUpgrade(String name, Formula priceFormula, Currency purchaseCurrency, boolean isPermanent, int x, int y, int width, int height, String displayScreen){
 
    super(name, priceFormula, purchaseCurrency, displayScreen);
    
    this.isBought = false;
    this.isUnlocked = false;
  }
  
  void buy(int levelAmount){
    if (!this.isBought && this.purchaseCurrency.amount.isGreaterEqualTo(this.price)){
      this.purchaseCurrency.amount.subtract(this.price);
      this.isBought = true;
    }
  }
  
  void reset(boolean isUnlocked){
    this.isBought = false;
    this.isUnlocked = isUnlocked;
  }
}
