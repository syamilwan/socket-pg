#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <unistd.h>

#define MAX 100
#define PORT 999

int main(){
	int sockfd, connfd, msg_size;
	struct sockaddr_in server, client;
	char send_data[MAX];
	char recv_data[MAX];	

	//Create socket for connection
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if(sockfd == -1){	
		printf("Socket creation failed!!!\n");
		exit(1);
	}
		
	//Assign IP and PORT
	memset(&server, 0, sizeof(server));
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = inet_addr("192.168.43.4");
	server.sin_port = htons(PORT);
	 
	//Binding socket
	if((bind(sockfd, (struct sockaddr*)&server, sizeof(server))) != 0){
		printf("Socket bind failed!!!\n");
		exit(1);
	}

	//Server listen to the connection
	if((listen(sockfd, 3)) != 0){
		printf("Connection failed!!!\n");
		exit(1);
	}
	
	msg_size = sizeof(client);

	//Accept data from client	
	connfd = accept(sockfd, (struct sockaddr*)&client, &msg_size);

	if(connfd < 0){
		printf("\nConnection failed!!!\n");
		exit(1);
	}
			
	for(;;){
		//Read from client
		memset(recv_data, 0, sizeof(recv_data));
		if(recv(connfd, recv_data, sizeof(recv_data), 0) < 0){
			printf("No message received!!!\n");
			exit(1);
		}
		else{
			//Print message from client
			printf("\nFrom client: %s", recv_data);
		}
	}
	printf("\nClosing connection....\n");
	close(sockfd);

	return 0;
}
