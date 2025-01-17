# Mit 6.102 software construction
## 1. Typescript start
### Snapshot diagrams
1. Snapshot diagrams 表现了程序运行时内部变量的状态
2. 依据想要表现的对象有所侧重
3. 当想要强调不可变对象时, 使用画双线
4. 注: 内存管理模型同C++

### Typescript Grammars
1. 可变对象与不可变对象
2. arrays, maps, sets
   - 变量声明
   - 迭代
   - 数组等缺失值问题
3. 变量声明: let
4. 对象声明: 对象: {}, 数组: []
5. enum枚举: 限定小范围可数的一些变量

### Ideas
1. typescript使用for循环, 尽量避免多加索引

## 2. 静态检查

### Background Knowledge
1. 静态类型检查: 是***Static checking(在编译时检查错误)***的一种方式, 静态类型检查是一种减少错误的方式, 即检查进行的运算有没有作用在错误的变量类型上;
2. Belief: 静态类型的使用对构建一个大型的软件系统尤为重要;
3. Typescript在进行静态类型检查之后会将静态类型信息丢弃;
4. 渐进类型: 由动态类型过度到静态类型(Typescript是向动态的Javascript中添加了静态元素);
   
### Static checking, dynamic checking, no checking
1. 一个语言可以提供如下的三种检查: static checking, dynamic checking, no checking, 静态检查 > 动态检查 > 不检查;
2. Typescript中为: 使用静态检查 + 不检查的方式, 因为typescript的动态检查依赖于javascript, 但是javascript不进行动态检查, 而是采取不检查的方式: y / 0 = infinity, 而不会在运行时报错(即说明没有动态检查);
   
### Typescript中的数字表示
Typescript中的数字表示存在一些corner cases
1. 极限精度问题: 超出了$SAFE_INTEGER$的精度就无法保证数字可以被精确的表示, 因为typescript中都是采取浮点数的方式表示数字, 并且浮点数没办法精确地表示;
   ```Typescript
   let x: number = 0;
    x += 0.1; x += 0.1;
    x += 0.1; x += 0.1;
    x += 0.1; x += 0.1;
    x += 0.1; x += 0.1;
    x += 0.1; x += 0.1;
    // x should be 1.0
    console.log(x);
    console.log('is x === 1.0?', x === 1.0);

    >>> false
   ```
2. 极值问题: Number.NaN, Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY;
3. 大小问题: 超出了number类所能表示的极限范围就会产生溢出问题;
4. Number是number的类包装器, 但在声明时应该仅使用number, 仅当要访问特殊数字时再使用Number;

### Function
1. 注释: 补全函数签名中没有涉及到的内容, 涉及到的内容不用写, 比如参数类型、返回值类型等
   - 函数功能
   - 输入
   - 输出
  
### mutating values and resassigning variables
> Immutability – intentionally forbidding certain things from changing at runtime – will be a major design principle in this course.
1. 不可变性分为两种情况: 值是否可变, 变量是否可以被重新赋值
2. 在写程序时, 尽量写多的不变变量, 减少代码中的变化

### Documenting assumptions
1. 在定义声明类型时, 例如const num: number = 10时, 实际上就已经记录了关于num的假设: 不变, 数字. 并且typescript会自动帮我们检查;
2. 如果一个数字需要始终大于0, 我们也需要记录(尽管typescript不会帮我们检查);
3. ***一个程序能够正常运行, 总会有很多假设, 我们需要记录这些假设!***
4. 程序有两个目标: 与电脑交流(保证程序的正确性), 与其他人交流(保证程序简洁易懂) 

### Goal of 6.102
1. safe from bugs: 正确性(当下可以正确运行)和防御性(以后可以正确运行)
2. easy to understand
3. ready for change: make it esay to make changes

### static checking => goal of course
1. SFB: 静态检查可以在程序运行前检查类型的错误问题
2. ETU: 显式地写出来每一个变量的类型, 相当于部分地documenting assumptions
3. RFC: 静态检查识别其他需要修改的地方