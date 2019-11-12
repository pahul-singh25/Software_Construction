from player import *
import pickle

def tuple_to_string(t):
    if isinstance(t,tuple):
        return str(t[0]+1) + '-' + str(t[1]+1)
    else: return t

def main():
    with open('go.config') as json_file:
        go_config = json.load(json_file)
        
    IP_ADD = go_config['IP']
    port_num = go_config['port']
    
    server_ADD = (IP_ADD, port_num)
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(server_ADD)

    P = remote_player(player(),s)
    #data = pickle.loads(s.recv(4096))
    #len_ = i = pickle.loads(s.recv(4096))
    
    
    sequence_num = 0
    #some change to data to make a list
    try:
        while True:
            i = pickle.loads(s.recv(4096))
            if i[0] == "register":
                if sequence_num != 0:
                    s.send(pickle.dumps('Go has going crazy!'))
                    break
                s.send(pickle.dumps("no name"))
            elif i[0] == "receive-stones":
                if sequence_num != 1:
                    s.send(pickle.dumps('Go has going crazy!'))
                    break
                P.set_stone(i[1])
                P.set_n()
            elif i[0] == "make-a-move":
                if sequence_num <= 1:
                    s.send(pickle.dumps('Go has going crazy!'))
                    break
                result = tuple_to_string(P.make_move(i[1]))
                s.send(pickle.dumps(result))
                
            sequence_num += 1
    except EOFError:
        pass

    s.close()

if __name__ == "__main__":
    main()