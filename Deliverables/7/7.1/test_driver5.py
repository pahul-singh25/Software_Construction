from rule_checker import *
from board import *
from player import *
import json
import sys

def tuple_to_string(t):
    if isinstance(t,tuple):
        return str(t[0]+1) + '-' + str(t[1]+1)
    else: return t

def read_json():
    
    input_list = [] 
    num_json = 0
    
    decoder = json.JSONDecoder()
    
    data_to_use = ''

    for data in sys.stdin: 
        
        data_to_use+= data.lstrip().rstrip('\n').rstrip() + ' '
            
    data = data_to_use.lstrip()
    while data:
        json_object,idx = decoder.raw_decode(data)
        input_list.append(json_object)	
        num_json += 1
        data = data[idx:].lstrip()	      

    return input_list



def main():
    ret = []
    data = read_json()
    P = player()
    # ########
    # k = -2 #
    # ########
    # data = [data[k]]
    # P.set_name("B")
    
    
    for i in data:
        if i[0] == "register":  
            ret.append("no name")
        elif i[0] == "receive-stones":
            P.set_stone(i[1])
            P.set_n(1)
        elif i[0] == "make-a-move":
            ret.append(tuple_to_string(P.make_move(i[1])))
    
    ret = json.dumps(ret)   
    print(ret)

if __name__ == "__main__":
    main()