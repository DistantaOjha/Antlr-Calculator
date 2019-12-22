import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

public class CalculatorBaseVisitorImpl extends CalculatorBaseVisitor<Double> {
    private HashMap<String, Double> variables = new HashMap<String, Double>();
    private HashMap<String, ArrayList<Double>> lists = new HashMap<>();
    
    @Override 
    public Double visitToSetVar(CalculatorParser.ToSetVarContext ctx) 
    { 
       System.out.println(">>ok!");
       return visitChildren(ctx); 
    }
    
   	@Override 
    public Double visitToExpr(CalculatorParser.ToExprContext ctx) 
    { 
      Double result = visitChildren(ctx);  
      System.out.println(">> " +  result);
      return result;
    }
    
    
    @Override
    public Double visitPlus(CalculatorParser.PlusContext ctx) {
        return visit(ctx.expr()) + visit(ctx.term());
    }
    
    @Override
    public Double visitMinus(CalculatorParser.MinusContext ctx) {
        return visit(ctx.expr()) - visit(ctx.term());
    }

    @Override
    public Double visitMultiplication(CalculatorParser.MultiplicationContext ctx) {
        return visit(ctx.term()) * visit(ctx.pow());
    }

    @Override
    public Double visitDivision(CalculatorParser.DivisionContext ctx) {
        return visit(ctx.term()) / visit(ctx.pow());
    }

    @Override
    public Double visitSetVariable(CalculatorParser.SetVariableContext ctx) {
        Double value = visit(ctx.expr());
        variables.put(ctx.ID().getText(), value);
        return value;
    }
 
    @Override
    public Double visitSetVariables(CalculatorParser.SetVariablesContext ctx) {
        Double value1 = visit(ctx.expr(0));
        variables.put(ctx.ID(0).getText(), value1);
        Double value2 = visit(ctx.expr(1));
        variables.put(ctx.ID(1).getText(), value2);
        return value1;
    }
    
    
    @Override 
    public Double visitSetList(CalculatorParser.SetListContext ctx) {
         String listString = ctx.list().getText();
         String[] listElements= listString.split(",");
         ArrayList<Double> arrList = new ArrayList<>();
         for(int i = 0; i< listElements.length; i++){
             arrList.add(Double.valueOf(listElements[i]));
         }
         lists.put(ctx.ID().getText(), arrList);
         return visitChildren(ctx); 
    }
    
    @Override
    public Double visitPower(CalculatorParser.PowerContext ctx) {
        if (ctx.pow() != null)
            return Math.pow(visit(ctx.unaryMinus()), visit(ctx.pow()));
        return visit(ctx.unaryMinus());
    }

    @Override
    public Double visitChangeSign(CalculatorParser.ChangeSignContext ctx) {
        return -1*visit(ctx.unaryMinus());
    }

    @Override
    public Double visitBraces(CalculatorParser.BracesContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitVariable(CalculatorParser.VariableContext ctx) {
        return variables.get(ctx.ID().getText());
    }
    
    @Override
    public Double visitPreIncre(CalculatorParser.PreIncreContext ctx) {
        String idname = ctx.ID().getText();
        variables.put(idname,variables.get(idname)+1);
        return variables.get(idname);
    }
    
    @Override
    public Double visitPostIncre(CalculatorParser.PostIncreContext ctx) {
        String idname = ctx.ID().getText();
        Double toReturn = variables.get(idname);
        variables.put(idname,toReturn +1);
        return toReturn;
    }
    
    
    @Override
    public Double visitTurnary(CalculatorParser.TurnaryContext ctx) {
        if(visit(ctx.expr(0))==0){
          return visit(ctx.expr(2));
        }
        else {
          return visit(ctx.expr(1));
        }
    }
    
    @Override
    public Double visitInt(CalculatorParser.IntContext ctx) {
        return Double.parseDouble(ctx.INT().getText());
    }
    
    @Override
    public Double visitDecimal(CalculatorParser.DecimalContext ctx) {
        return Double.parseDouble(ctx.DECIMAL().getText());
    }
    
    @Override
    public Double visitScientific(CalculatorParser.ScientificContext ctx) {
        return Double.parseDouble(ctx.SCI().getText());
    }
    
    @Override 
    public Double visitListSize(CalculatorParser.ListSizeContext ctx) { 
        return new Double(lists.get(ctx.ID().getText()).size());
    }

	  @Override
    public Double visitListElement(CalculatorParser.ListElementContext ctx) {
       int index = visit(ctx.expr()).intValue();
       String listName= ctx.ID().getText();
       return new Double(lists.get(listName).get(index)); 
    }
    
}
