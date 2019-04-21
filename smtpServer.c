#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <unistd.h>
#include <stdbool.h>

#define MAX 111
#define PORT 999

int main(){
	int sockfd, connfd, msg_size;
	struct sockaddr_in server, client;
	char send_data[MAX];
	char recv_data[MAX];	
	FILE *fp; //file pointer
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
		connfd = accept(sockfd, (struct sockaddr*)&client, &msg_size);
		//Read from client
		memset(recv_data, 0, sizeof(recv_data));
		if(recv(connfd, recv_data, sizeof(recv_data), 0) < 0){
			printf("No message received!!!\n");
			//exit(1);
		}
		else{
			//Print message from client
			printf("\nFrom client: %s", recv_data);
			
			//inserting data into a new file
			char filename[MAX], content[MAX], recipient[MAX], title[MAX], message[MAX];
			int i=0, j=0;
			bool stop=false;
			printf("\nRecipient: ");
			do{
			    if(recv_data[i]!=';'){
				    printf("%c",recv_data[i]);
				    recipient[j] = recv_data[i];
				    i++,j++;
			    }
			    else
				    stop=true;
			}while(stop==false);
				
			printf("\nTitle: ");j=0;stop=false;
			do{
			    if(recv_data[i]!=';'){
				    printf("%c",recv_data[i]);
				    title[j] = recv_data[i];
				    i++,j++;
			    }
			    else
				    stop=true;
			}while(stop==false);
			
			printf("\nMessage: ");j=0;stop=false;
			do{
			    if(recv_data[i]!=';'){
				    printf("%c",recv_data[i]);
				    message[j] = recv_data[i];
				    i++,j++;
			    }
			    else
				    stop=true;
			}while(stop==false);
			printf("\n");
			
			//combine and sort strings
			strcat(filename,recipient);
			strcat(filename,title);
			strcat(filename,".txt");
			strcat(content,recipient);
			strcat(content,";");
			strcat(content,title);
			strcat(content,";");
			strcat(content,message);
			strcat(content,";");
			//creating new file and writes into it
			fp = fopen(filename, "w");
			fprintf(fp, content);
			fclose(fp);
		}
	}
	printf("\nClosing connection....\n");
	close(sockfd);
	return 0;
}
		
				
				
				
