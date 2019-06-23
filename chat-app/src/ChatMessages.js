import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import ChatMessage from './ChatMessage';
import './ChatMessages.css';

const QUERY_DATA=gql`query getMessages($roomId:ID!){
  messages:getRoomMessages(roomId:$roomId){
      user{name},
      message,
      id
   }
}
`;

const SUBSCRIBE_TO_DATA=gql`
subscription 
  dummySub($roomId:ID!){
     message:newChatMessages(roomId:$roomId){
       id,
       message,
       user{name}
  }
}
`;

class StreamingComponent extends React.Component {
    componentDidMount(){
        this.props.subscribeToNewMessages();
    }
    render(){
        console.log("StreamingComponent render data="+JSON.stringify(this.props.data));
        return (
	    <div className="container" >
              {this.props.data.messages.map(msg =>
                                 <ChatMessage key={msg.id} message={{user:msg.user.name,text:msg.message}}/>)
              }</div>
        );
    }
};

export default class ChatMessages extends Component {
    render() {
        return (
	    <Query query={QUERY_DATA}
                   variables={{roomId:1}}
            >
	      {({ subscribeToMore, loading, error, data }) => {
                  if (loading) return <div>Fetching</div>;
	          if (error) return <div>Error</div>;
		  return (
                      <StreamingComponent
                        data={data}
                        subscribeToNewMessages={()=>subscribeToMore({
                            document: SUBSCRIBE_TO_DATA,
                            variables: {roomId:1},
                            updateQuery: (prev,{subscriptionData})=>{
                                if (!subscriptionData.data) return prev;
                                const message = subscriptionData.data.message;
                                console.log("prev count"+prev.length);
                                console.log("prev"+JSON.stringify(prev));
                                const res = Object.assign({},
                                                     prev,
                                                     {
                                                         messages: [ message, ...prev.messages]});
                                console.log("res"+JSON.stringify(res));
                                return res;
                            }
                        })}
                      />
/*		      <div className="container" >{data.messages.map(msg => <ChatMessage key={msg.id} message={{user:msg.user.name,text:msg.message}}/>)}</div>
                      </StreamingComponent>  */
		  );
	      }}
	    </Query>
        );
  }
}
