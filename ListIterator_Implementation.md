# ListIterator Implementation for SingleLinkedList

## Overview
I've successfully implemented a comprehensive ListIterator for the `SingleLinkedList<T>` class. The implementation includes both a basic `Iterator<T>` and a full `ListIterator<T>` interface.

## Key Features

### 1. Interface Implementation
- **Iterable<T>**: Makes the SingleLinkedList work with enhanced for loops
- **Iterator<T>**: Provides basic forward iteration capabilities
- **ListIterator<T>**: Provides advanced list iteration with modification capabilities

### 2. Methods Implemented

#### Basic Iterator Methods
- `hasNext()`: Returns true if there are more elements
- `next()`: Returns the next element and advances the cursor
- `remove()`: Removes the last element returned by next()

#### ListIterator-Specific Methods
- `hasPrevious()`: Returns false (not efficiently supported for singly-linked lists)
- `previous()`: Throws UnsupportedOperationException (not efficiently supported)
- `nextIndex()`: Returns the index of the next element
- `previousIndex()`: Returns the index of the previous element
- `set(T e)`: Replaces the last element returned by next()
- `add(T e)`: Inserts an element at the current position

#### Factory Methods
- `iterator()`: Returns a basic iterator starting from the beginning
- `listIterator()`: Returns a list iterator starting from the beginning
- `listIterator(int index)`: Returns a list iterator starting from the specified index

## Important Design Considerations

### 1. Singly-Linked List Limitations
Since this is a singly-linked list, certain operations are inherently inefficient:
- **Previous traversal**: Not efficiently supported because we can't go backward without traversing from the head
- **hasPrevious()**: Always returns false
- **previous()**: Throws UnsupportedOperationException

### 2. Iterator State Management
The iterator maintains several important state variables:
- `current`: Points to the next node to be returned
- `previous`: Points to the last node returned by next()
- `currentIndex`: Tracks the current position in the list
- `canRemove`: Tracks whether remove() can be called
- `canSet`: Tracks whether set() can be called

### 3. Modification Operations
The implementation properly handles:
- **Adding elements**: Supports insertion at any position during iteration
- **Removing elements**: Safely removes the last element returned by next()
- **Setting elements**: Replaces the data in the last element returned by next()

## Usage Examples

### Basic Iteration
```java
SingleLinkedList<String> list = new SingleLinkedList<>();
// ... add elements ...

// Using basic iterator
Iterator<String> iter = list.iterator();
while (iter.hasNext()) {
    String element = iter.next();
    System.out.println(element);
}

// Using enhanced for loop
for (String element : list) {
    System.out.println(element);
}
```

### Advanced ListIterator Operations
```java
ListIterator<String> listIter = list.listIterator();

// Add element at current position
listIter.add("New Element");

// Get next element and modify it
if (listIter.hasNext()) {
    String element = listIter.next();
    listIter.set("Modified " + element);
}

// Remove the element we just modified
listIter.remove();
```

### Starting from Specific Position
```java
// Start iteration from index 2
ListIterator<String> listIter = list.listIterator(2);
while (listIter.hasNext()) {
    System.out.println("Index " + listIter.nextIndex() + ": " + listIter.next());
}
```

## Error Handling
The implementation includes proper error handling:
- `NoSuchElementException`: When calling next() with no more elements
- `IllegalStateException`: When calling remove() or set() inappropriately
- `UnsupportedOperationException`: When calling previous() (not supported)
- `IndexOutOfBoundsException`: When creating iterator with invalid index

## Testing
The implementation has been thoroughly tested with the `IteratorDemo` class, which demonstrates:
- Basic iteration functionality
- Enhanced for loop compatibility
- ListIterator advanced features
- Modification operations (add, remove, set)
- Error handling scenarios
- Starting iteration from specific positions

## Conclusion
This ListIterator implementation provides a robust and feature-complete iteration interface for the SingleLinkedList class while respecting the limitations of singly-linked data structures. It follows Java's Iterator and ListIterator contracts and provides appropriate error handling for edge cases.