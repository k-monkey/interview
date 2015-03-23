class DLinkedNode:
    def __init__(self, key, value):
        self.val = value
        self.key = key
        self.prev = None
        self.next = None
        

class LRUCache:

    # @param capacity, an integer
    def __init__(self, capacity):
        self.timesorted_list_head = None
        self.timesorted_list_tail = None
        self.keyval_map = {}
        self.cap = capacity

    # @return an integer
    def get(self, key):
        if key in self.keyval_map:
            dl_node = self.keyval_map[key]
            if dl_node:
                if dl_node.prev == None: #already head of the list
                    return dl_node.val #do nothing to the timesorted_list, simply return
                else:
                    dl_node.prev.next = dl_node.next
                    dl_node.prev = None
                    dl_node.next = self.timesorted_list_head
                    self.timesorted_list_head = dl_node
                    return dl_node.val
            else:
                pass #log error message here
                return -1

    # @param key, an integer
    # @param value, an integer
    # @return nothing
    def set(self, key, value):
        if not key:
            pass #log some error for bad input
            return 
        
        if value <= 0:
            pass #log some error for bad input
            return 
        
        if not key in self.keyval_map:
            if len(self.keyval_map) >= self.cap: #cap exceeded, remove LRU
                oldkey = self.timesorted_list_tail.key
                self.keyval_map.pop(oldkey, None)
                if self.timesorted_list_tail.prev: #tail is not head
                    self.timesorted_list_tail = self.timesorted_list_tail.prev
                else:
                    self.timesorted_list_tail = None
                    self.timesorted_list_head = None
            
            #set new key
            dl_node = DLinkedNode(key, value)
            if len(self.keyval_map) == 0: #cache empty 
                self.timesorted_list_head = dl_node
                self.timesorted_list_tail = dl_node
            else:
                dl_node.next = self.timesorted_list_head
                self.timesorted_list_head = dl_node
            self.keyval_map[key] = dl_node