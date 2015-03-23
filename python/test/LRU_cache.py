class DLinkedNode:
    def __init__(self, key, value):
        self.val = value
        self.key = key
        self.prev = None
        self.next = None
        

#        ast executed input: 	10,[set(10,13),set(3,17),set(6,11),set(10,5),set(9,10),get(13),set(2,19),get(2),get(3),set(5,25),get(8),set(9,22),set(5,5),set(1,30),get(11),set(9,12),get(7),get(5),get(8),get(9),set(4,30),set(9,3),get(9),get(10),get(10),set(6,14),set(3,1),get(3),set(10,11),get(8),set(2,14),get(1),get(5),get(4),set(11,4),set(12,24),set(5,18),get(13),set(7,23),get(8),get(12),set(3,27),set(2,12),get(5),set(2,9),set(13,4),set(8,18),set(1,7),get(6),set(9,29),set(8,21),get(5),set(6,30),set(1,12),get(10),set(4,15),set(7,22),set(11,26),set(8,17),set(9,29),get(5),set(3,4),set(11,30),get(12),set(4,29),get(3),get(9),get(6),set(3,4),get(1),get(10),set(3,29),set(10,28),set(1,20),set(11,13),get(3),set(3,12),set(3,8),set(10,9),set(3,26),get(8),get(7),get(5),set(13,17),set(2,27),set(11,15),get(12),set(9,19),set(2,15),set(3,16),get(1),set(12,17),set(9,1),set(6,19),get(4),get(5),get(5),set(8,1),set(11,7),set(5,2),set(9,28),get(1),set(2,2),set(7,4),set(4,22),set(7,24),set(9,26),set(13,28),set(11,26)]

class LRUCache:

    # @param capacity, an integer
    def __init__(self, capacity):
        self.timesorted_list_head = None
        self.timesorted_list_tail = None
        self.keyval_map = {}
        self.cap = capacity

    # @return an integer
    def get(self, key):
        print("get(%s)", str(key))
        if key in self.keyval_map:
            dl_node = self.keyval_map[key]
            if dl_node:
                if dl_node.prev == None: #already head of the list
                    print "return1 " + str(dl_node.val)
                    return dl_node.val #do nothing to the timesorted_list, simply return
                else:
                    dl_node.prev.next = dl_node.next
                    if dl_node.next == None: #already tail of the list
                        self.timesorted_list_tail = dl_node.prev
                    dl_node.prev = None
                    dl_node.next = self.timesorted_list_head
                    self.timesorted_list_head.prev = dl_node
                    self.timesorted_list_head = dl_node
                    print "return2 " + str(dl_node.val)
                    return dl_node.val
            else:
                #log error message here
                print "return3 -1"
                return -1

    # @param key, an integer
    # @param value, an integer
    # @return nothing
    def set(self, key, value):
        print("set(%s,%s)", str(key), str(value))
        if not key:
            pass #log some error for bad input
            return 
        
        if value <= 0:
            pass #log some error for bad input
            return 
        
        if not key in self.keyval_map:
            if len(self.keyval_map) >= self.cap: #cap exceeded, remove LRU
                oldkey = self.timesorted_list_tail.key
                self.keyval_map.pop(oldkey)
                if self.timesorted_list_tail.prev: #tail is not head
                    self.timesorted_list_tail = self.timesorted_list_tail.prev
                    self.timesorted_list_tail.next = None
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
                self.timesorted_list_head.prev = dl_node
                self.timesorted_list_head = dl_node
                if len(self.keyval_map) == 1:
                    self.timesorted_list_tail.prev = dl_node
            self.keyval_map[key] = dl_node
        self.println()
        
    def println(self):
        print "==========printing all============"
        #print self.keyval_map
        node = self.timesorted_list_head
        line = ""
        while node:
            line += str(node.key) + "->"
            node = node.next
        print line + "*"
        print("head = %s"%str(self.timesorted_list_head.key))
        print("tail = %s"%str(self.timesorted_list_tail.key))