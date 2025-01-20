"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.hailstone = hailstone;
/**
 * Compute a hailstone sequence.
 * @param n starting number for sequence.
 * @returns hailstone sequence starting with n and ending with 1.
 *          undefined if n <= 0
 */
function hailstone(n) {
    if (n <= 0) {
        return undefined;
    }
    var arr = new Array;
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
var arr = ["abc"];
var obj = arr;
console.log(arr.toString());
