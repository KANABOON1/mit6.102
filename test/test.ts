
let arr: Array<number> = new Array<number>;
arr.push(1);
console.log(arr);
let n: number = 3;
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