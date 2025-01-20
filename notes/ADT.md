# Mit 6.102 software construction
## 7. Abstract data types
### Abstraction
- Abstraction. Omitting or hiding low-level details with a simpler, higher-level idea.
- Modularity. Dividing a system into components or modules, each of which can be designed, implemented, tested, reasoned about, and reused separately from the rest of the system.
- Encapsulation. Building a wall around a module so that the module is responsible for its own internal behavior, and bugs in other parts of the system can’t damage its integrity.
- Information hiding. Hiding details of a module’s implementation from the rest of the system, so that those details can be changed later without changing the rest of the system.
- Separation of concerns. Making a feature (or “concern”) the responsibility of a single module, rather than spreading it across multiple modules.
  
### user defined types
数据抽象的核心思想: 一个类型可以对其执行的操作来定义, 类型的使用者不再需要知道具体是如何存储的, 只需要知道能进行什么操作.

### classifying types and operations
一个ADT的operations可以被划分为:
- Creators: create new objects of the type, 动态方法或者静态方法(又叫factory), 参数为其他类型, 结果为当前类型;
- Producers: create new objects of the type, 但是参数需要有当前类型的对象
- Observers: take objects of the abstract type and return objects of a different type
- Mutators: change objects

### Representation independence
1. client只是根据ADT的公共方法的spec进行调用, 而不能看到具体细节. 因此, 修改ADT的具体实现并不影响client的调用; 注: client只能访问ADT的方法, 不能访问具体的成员(即使是public) 
2. 使用defensive copying可以避免对象被更改

## 8. Abstraction Functions & Rep Invariants(Immutable ADT)
### Avoid rep exposure
1. rep exposure: 外部代码可以直接修改表示, 而不经过ADT指定的函数;
2. 仔细检查ADT的参数和返回值类型, 对于可变对象, 使用defensive copying, 避免直接返回可变数据的直接reference;
3. 注: 仔细检查有无可变对象
4. 解决方案: 
   - 对于immutable: 使用private + readonly, 或者public + readonly
   - 对于mutale: 使用readonly + defensive copying

### Rep invariant and abstract function
1. 设计ADT时的考虑要素: 
   - abstract space: 要表示的对象的值空间
   - rep space: 使用的表示方法的空间
   - rep invariant: 决定哪些representation是合法的, 是对于rep space中值的要求
   - abstract function: 将符合rep invariant的对象映射到abstract space中的对象的函数

### Checking rep invariant
1. 在ADT的impl中时刻保持rep invariant, 与loop invariant意义相同;
2. 使用checkRep函数在每次函数即将返回时对rep invariant进行检查;

### No null values in the rep
1. 在设计ADT时避免使用null或者undefined
2. 除非显式说明, 否则默认不能等于null或者undefined, 在有strict null-checking情况下, checkRep中也不需要写null检查;

### Benevolent side-effects
1. ADT的immutable指的是其表示的在abstract space中的值不变, 而其内部rep可以改变, 这种rep的改变是被允许的并且有时是有益的;

### Documenting the RI and AF
1. 需要写好RI, AF, rep exposure safety argument
2. 示例: ![example of documenting RI, AF and RESA](img/image9.png)

### ADT invariants replace proconditions
1. 使用专门设计的ADT代替函数的前置条件![alt text](img/image10.png)

### Write an abstract data type
1. Spec: 为这个data type的opertaion写spec(一个data type由其operations决定), 此时不涉及AF以及Rep;
2. Test
3. Implement: 
   - 写清楚该类的private fields(即内部表示), 为rep写注释说明, 包括rep invariant, abstract function以及rep exposure argument; 
   - Assert rep invariant: 写一个checkRep函数
   - Implement operations: 为每一个operation写impl, 注意要在每一个operation中都调用checkRep

### Writing a program
![Writing a program](img/image11.png)

## 9. Defining ADTs with Interfaces, Generics, Enums, and Functions
### Interface
1. Interface可以仅仅specify the contract for the client, 而不需要具体的实现(rep free)
2. Interface下可以有很多种对于该Interface的表示方法

### Subclassing

### Generic types
### Enumeration

## Functional programming
> modeling problems and implementing systems with immutable data types and operations that implement pure functions

### First-class functions
1. 在tyoescript中, 函数是first-class的, 意味着函数可以像变量一样被传递;

### Iterator
1. Iterator是一种设计模式, 可以为client提供统一的处理序列的方式, 否则在使用list/set等方式处理序列时需要修改部分代码, 不够完善;![iterator](img/image12.png)
2. Iterator是可变的, 因此尽量不要在循环进行时修改Iterator的内置数据, 例如array

### map/filter/reduction
1. 使用map, filter and reduction处理Iterable type, 可以跳过复杂的循环过程、中间变量、以及避免直接修改可变对象; 另外, 由于返回值为同类型, 故可以进行链式操作;
2. 可以使用map, filter, reduction进行类似于python中的列表推导式的操作;