import { Parser, ParseTree, compile, visualizeAsUrl } from 'parserlib';

/** Immutable type representing an integer arithmetic expression. */
export interface IntegerExpression {
    // Data type definition
    //    IntegerExpression = Constant(n:number)
    //                        + Plus(left:IntegerExpression, right:IntegerExpression)
    //                        + Times(left:IntegerExpression, right:IntegerExpression)

    /** @returns the computed value of this expression */
    value(): number;
}

export class Constant implements IntegerExpression {
    
    // Abstraction function
    //    AF(n) = the integer n
    // Rep invariant
    //    true
    // Safety from rep exposure
    //    all fields are immutable and unreassignable
    
    /** Make a Constant. */
    public constructor(private readonly n: number) {
    }
    
    public value(): number {
        return this.n;
    }
    
    public toString(): string {
        return "Constant(" + this.n + ")";
    }
}

export class Plus implements IntegerExpression {
    
    // Abstraction function
    //    AF(left, right) = the expression left + right
    // Rep invariant
    //    true
    // Safety from rep exposure
    //    all fields are immutable and unreassignable
    
    /** Make a Plus which is the sum of left and right. */
    public constructor(private readonly left: IntegerExpression,
                       private readonly right: IntegerExpression) {
    }
    
    public value(): number {
        return this.left.value() + this.right.value();
    }
    
    public toString(): string {
        return "Plus(" + this.left + "," + this.right + ")";
    }
}

export class Times implements IntegerExpression {
    
    // Abstraction function
    //    AF(left, right) = the expression left * right
    // Rep invariant
    //    true
    // Safety from rep exposure
    //    all fields are immutable and unreassignable
    
    /** Make a Times which is the product of left and right. */
    public constructor(private readonly left: IntegerExpression,
                       private readonly right: IntegerExpression) {
    }
    
    public value(): number {
        return this.left.value() * this.right.value();
    }
    
    public toString(): string {
        return "Times(" + this.left + "," + this.right + ")";
    }
}
