from rule_checker import *
from board import *
from player import *
import json
import sys
import socket

def tuple_to_string(t):
    if isinstance(t,tuple):
        return str(t[0]+1) + '-' + str(t[1]+1)
    else: return t

def read_json():
    
    input_list = [] 
    
    decoder = json.JSONDecoder()
    
    data_to_use = ''

    for data in sys.stdin: 
        
        data_to_use+= data.lstrip().rstrip('\n').rstrip() + ' '
            
    data = data_to_use.lstrip()
    while data:
        json_object,idx = decoder.raw_decode(data)
        input_list.append(json_object)	
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
    P.set_n(1)
    
    for i in data:
        if i[0] == "register":  
            ret.append("no name")
        elif i[0] == "receive-stones":
            P.set_name(i[1])
        elif i[0] == "make-a-move":
            ret.append(tuple_to_string(P.make_move(i[1])))
    
    ret = json.dumps(ret)   
    print(ret)


def new_main():
    #set up the port 
    with open('go.config') as json_file:
        go_config = json.load(json_file)
    
    print(go_config)
    IP_ADD = go_config['IP']
    port_num = go_config['port']
    
    server_ADD = (IP_ADD, port_num)
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind(server_ADD)

    #create remote_player object and start the connection
    P = remote_player(player(),s)
    #s.connect()

    data = read_json()
    sequence_num, ret = 0, []


    for i in data:
        if i[0] == "register":
            if sequence_num != 0:
                ret.append('Go has going crazy!')
                s.close()
                break
            ret.append("no name")
        elif i[0] == "receive-stones":
            if sequence_num != 1:
                ret.append('Go has going crazy!')
                s.close()
                break
            s.send(i[1])
            P.set_name()
        elif i[0] == "make-a-move":
            if sequence_num <= 1:
                ret.append('Go has going crazy!')
                s.close()
                break
            s.send(i[1])
            P.make_move()
            result = s.recv()
            ret.append(tuple_to_string(result))
            
        sequence_num += 1

    ret = json.dumps(ret)
    print(ret)


if __name__ == "__main__":
    new_main()