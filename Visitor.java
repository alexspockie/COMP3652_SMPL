/**
 * The generic Visitor interface for the Arithmetic parser
 * example.
 * @param <S> The type of the information needed by the visitor
 * @param <T> The type of result returned by the visitor 
 */
public interface Visitor<S, T> {

    // to facilitate generic constructors
    public S getDefaultState();

    // program
    public T visitArithProgram(ArithProgram p, S arg) throws VisitException,NoSuchMethodException;

    // statements
    public T visitStatement(Statement exp, S arg) throws VisitException, NoSuchMethodException ;
    public T visitStmtSequence(StmtSequence exp, S arg) throws VisitException , NoSuchMethodException;
    public T visitStmtDefinition(StmtDefinition sd, S arg) throws VisitException, NoSuchMethodException;

    //public T visitStmtFunDefn(StmtFunDefn fd, S arg) throws VisitException, NoSuchMethodException; // fun def
    public T visitStmtExpDefn (StmtExpDefn proc,S arg) throws VisitException, NoSuchMethodException; // would do the assigning of values in exp
    public T visitExpBind(ExpBind bind, S arg) throws VisitException,NoSuchMethodException; // would do temp binding for let so extend environment
    public T visitExpProcedure (ExpProcedure proc,S arg) throws VisitException, NoSuchMethodException; //would return the object?
    public T visitExpFunCall(ExpFunCall fc, S arg) throws VisitException, NoSuchMethodException; //fun call would run the function
    public T visitExpCClause(ExpCClause clause,S arg) throws VisitException,NoSuchMethodException; //clauses for case - when evaluated runs the exp compare ad returns result of consequent
    public T visitExpCall(ExpCall call, S arg) throws VisitException, NoSuchMethodException; //call - evaluated runs proc using variables in list returns result
    public T visitExpLet(ExpLet let,S arg) throws VisitException, NoSuchMethodException;//let - evaluates bindings one by one and then evaluates stmt sequence
    public T visitExpCase(ExpCase ecase, S arg) throws VisitException, NoSuchMethodException;//case - evaluates each predicate and returns result;
    public T visitExpSequence(ExpSequence seq, S arg)throws VisitException, NoSuchMethodException; //returns value of last expression
    public T visitMultiExp(MultiValExp exp, S arg) throws VisitException, NoSuchMethodException;//returns a tuple of the result of each expression
    public T visitStmtMulDef(StmtMulDef muldef, S exp)throws VisitException, NoSuchMethodException; //evaluate exp first which returns tuple of values then assign to variables
    public T visitRead(ExpRead read,S arg) throws VisitException, NoSuchMethodException;//readtype determines the kind of input read from keyboard
    public T visitPrint(ExpPrint print, S arg) throws VisitException,NoSuchMethodException;// evaluates expression then prints result to the screen
    public T visitExpList(ExpList lst,S arg) throws VisitException,NoSuchMethodException; //evaluates expressions and returns a SMPLList object
    public T visitExpVector(ExpVector vec,S arg)throws VisitException,NoSuchMethodException;//evaluates expressions and returns a vector object
    public T visitExpVecCall(ExpVecCall vc, S arg)throws VisitException,NoSuchMethodException;//evaluates expression and returns the objetc at the space

    // arithmetic and char expressions
    public T visitExpCompare(ExpCompare exp, S arg) throws VisitException, NoSuchMethodException ; //comparisons
    public T visitExpIfThen(ExpIfThen exp, S arg) throws VisitException, NoSuchMethodException ;//if
    public T visitExpAdd(ExpAdd exp, S arg) throws VisitException,NoSuchMethodException ;//add
    public T visitExpSub(ExpSub exp, S arg) throws VisitException,NoSuchMethodException;//sub
    public T visitExpMul(ExpMul exp, S arg) throws VisitException,NoSuchMethodException;//mul
    public T visitExpDiv(ExpDiv exp, S arg) throws VisitException,NoSuchMethodException;//div
    public T visitExpMod(ExpMod exp, S arg) throws VisitException,NoSuchMethodException;//mod
    public T visitExpLit(ExpLitInt exp, S arg) throws VisitException,NoSuchMethodException;//literal interger
    public T visitExpDouble(ExpLitDouble exp, S arg) throws VisitException,NoSuchMethodException;//literal double
    
    public T visitExpVar(ExpVar exp, S arg) throws VisitException,NoSuchMethodException;//var
}
