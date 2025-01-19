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