#include <unistd.h> 
#include <stdio.h> 
#include <sys/socket.h> 
#include <stdlib.h> 
#include <netinet/in.h> 
#include <string.h> 
#include <time.h>

#define PORT 999

int main(int argc, char const *argv[]){ 	
	setbuf(stdout,NULL);

	int server_fd, new_socket, valread; 
	struct sockaddr_in address; 
	int opt = 1; 
	int addrlen = sizeof(address); 
	char buffer[1024] = {0}; 
	char *hello = "Msg from Server: \nConnected"; 

	// Creating socket file descriptor 
	if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0) { 
		perror("socket failed");
		exit(EXIT_FAILURE);
	} 

	// Forcefully attaching socket to the port 25
	address.sin_family = AF_INET; 
	address.sin_addr.s_addr = INADDR_ANY; 
	address.sin_port = htons( PORT ); 

	printf("\t***Server socket open\n\n");

	// Forcefully attaching socket to the port 25
	if (bind(server_fd, (struct sockaddr *)&address,sizeof(address))<0) { 
	   perror("bind failed"); 
	   exit(EXIT_FAILURE); 
	} 
	
	if (listen(server_fd, 3) < 0) { 
	     perror("listen"); 
	     exit(EXIT_FAILURE); 
	} 

	if ((new_socket = accept(server_fd, (struct sockaddr *)&address, (socklen_t*)&addrlen))<0) { 
	     perror("accept"); 
	     exit(EXIT_FAILURE); 
	} 
	send(new_socket , hello , strlen(hello) , 0 ); 
	
	for(;;){
		//Read from client
		int data;
		memset(buffer, 0, sizeof(buffer));
		if(recv(server_fd, buffer, sizeof(buffer), 0) < 0){
			printf("No message received!!!\n");
			exit(1);
		}
		else{
			//Print message from client
			printf("\nRecevied : %s", buffer);
		}

	}	
	//valread = read(new_socket , buffer, 1024); 
	
	printf("%s\n",buffer); 
	printf("\n\t***Server socket closed\n\n");	
	
	//send(new_socket , hello , strlen(hello) , 0 ); 
}
