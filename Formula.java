

class Formula{
  
  String formula;
  
  Formula(String formula){
    this.formula = formula;
  }
  
  int calculate(int x){
    if (this.formula == "(x^2)*")
      return (int)Math.pow(x, 2);
    return 1;
  }
  
  

}