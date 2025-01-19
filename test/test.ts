/**
 * Compute a hailstone sequence.
 * @param n starting number for sequence.
 * @returns hailstone sequence starting with n and ending with 1.
 *          undefined if n <= 0
 */
export function hailstone(n: number): Array<number> | undefined {

    if (n <= 0) {
        return undefined;
    }

    let arr: Array<number> = new Array<number>;
    while (n != 1) {
        arr.push(n);
        if (n % 2 === 0) {
            n = n / 2;
        }
        else {
            n = 3 * n + 1;
        }
    }
    arr.push(n);
    return arr;
}

class Wallet {
    private amount: number = 0;

    public loanTo(that: Wallet): void {
/*A*/         that.amount += this.amount;
/*B*/         this.amount = 0;
    }
}

class Person {
    private w: Wallet;

    public getNetWorth(): number {
/*C*/         return this.w.amount;
    }

    public isBroke(): boolean {
/*D*/         return Wallet.amount === 0;
    }
}


function main(): void {
/*E*/     const w = new Wallet();
/*F*/     w.amount = 100;
/*G*/     w.loanTo(w);
}

main();