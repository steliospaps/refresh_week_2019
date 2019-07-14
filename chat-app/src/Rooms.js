import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import Room from './Room';
import RoomCreator from './RoomCreator.js';
//this does not work as expected
// looks like apollo-client does not support @defer correctly currently.
//const QUERY_ROOMS = gql`query {rooms:getAllRooms{id,name,relevance @defer relevance}}`;
const QUERY_ROOMS = gql`query {rooms:getAllRooms{id,name}}`;
const SUBSCRIBE_ROOMS = gql`subscription {room:newRooms{id,name}}`;

class StreamingRoom extends Component {
    componentDidMount(){
        this.props.subscribe();
    }
    render(){
        return (
            <div>{this.props.data.rooms.map(room => <Room key={room.id} room={room} onEnterRoom={this.props.onEnterRoom} selected={this.props.room && this.props.room.id === room.id} />)}</div>
        );
    }
}

export default class Rooms extends Component {        
    render() {
        return (
            <div>
              <Query query={QUERY_ROOMS} >
                {({ subscribeToMore, loading, error, data }) => {
                    if (loading) return <div>Fetching</div>;
                    if (error) return <div>Error</div>;
                    console.log(JSON.stringify(data));
                    return (
                        <StreamingRoom
                          data={data}
                          onEnterRoom={this.props.onEnterRoom}
                          room={this.props.room}
                          subscribe={()=>subscribeToMore({
                              document: SUBSCRIBE_ROOMS,
                              //variables: {}, // no variables
                              updateQuery: (prev,{subscriptionData}) =>{
                                  if(!subscriptionData.data) return prev;
                                  const room = subscriptionData.data.room;
                                  const res = Object.assign({},
                                                            prev,
                                                            {
                                                                rooms: [...prev.rooms,room]
                                                            });
                                  return res;
                              }
                          })}
                        />
                    );
                }}
              </Query>
              <RoomCreator/>
            </div>
        );
    }
}
