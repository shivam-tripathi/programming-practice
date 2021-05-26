import copy
from typing import List

class SelfReferencingEntity:
    def __init__(self):
        self.parent = None

    def set_parent(self, parent):
        self.parent = parent

class SomeComponent:
    def __init__(self, t_int: int, t_list: List[object], t_circ: SelfReferencingEntity) -> None:
        self.t_int = t_int
        self.t_list = t_list
        self.t_circ = t_circ

    '''
    Shallow copy
    '''
    def __copy__(self):
        copy_t_int = self.t_int
        copy_t_list = copy.copy(self.t_list)
        copy_t_circ = copy.copy(self.t_circ)
        new = self.__class__(copy_t_int, copy_t_list, copy_t_circ)
        new.__dict__.update(self.__dict__)
        return new

    '''
    Deep copy
    What is the use of the argument `memo`? Memo is the dictionary that is
    used by the `deepcopy` library to prevent infinite recursive copies in
    instances of circular references. Pass it to all the `deepcopy` calls
    you make in the `__deepcopy__` implementation to prevent infinite
    recursions.
    '''
    def __deepcopy__(self, memo={}):
        deepcopy_t_int = self.t_int
        deepcopt_t_list = copy.deepcopy(self.t_list, memo)
        deepcopy_t_circ = copy.deepcopy(self.t_circ, memo)
        new = self.__class__(deepcopy_t_int, deepcopt_t_list, deepcopy_t_circ)
        new.__dict__ = copy.deepcopy(self.__dict__, memo)
        return new


if __name__ == "__main__":
    list_of_objects = [1, {1, 2, 3}, [1, 2, 3]]
    circular_ref = SelfReferencingEntity()
    component = SomeComponent(23, list_of_objects, circular_ref)
    circular_ref.set_parent(component)

    shallow_copied_component = copy.copy(component)
    shallow_copied_component.t_list.append("another object")
    if component.t_list[-1] == "another object":
        print('Adding items to shallow copys list shows up in original list')
    else:
        print('Adding items to shallow copys list shows up in original list')

    component.t_list[1].add(4)
    if 4 in shallow_copied_component.t_list[1]:
        print("Adding items to original objects list shows up in shallow copy")
    else:
        print("Adding items to original objects list doesnt show up in shallow copy")

    # Deep copy
    deep_copied_component = copy.deepcopy(component)
    deep_copied_component.t_list.append("one more object")
    if component.t_list[-1] == "one more object":
        print("Adding items to deep copys list shows up in original list")
    else:
        print("Adding items to deep copys list doesnt show up in original list")

    # Let's change the set in the list of objects.
    component.t_list[1].add(10)
    if 10 in deep_copied_component.t_list[1]:
        print("Adding items in original list shows up in deep copy")
    else:
        print("Adding items in original list doesnt show up in deep copy")


    print('Deepcopy and circular ref have same reference:')
    print(
        f"id(deep_copied_component.t_circ.parent): "
        f"{id(deep_copied_component.t_circ.parent)}"
    )
    print(
        f"id(deep_copied_component.t_circ.parent.t_circ.parent): "
        f"{id(deep_copied_component.t_circ.parent.t_circ.parent)}"
    )
