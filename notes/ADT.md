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

## 8. Abstraction Functions & Rep Invariants
