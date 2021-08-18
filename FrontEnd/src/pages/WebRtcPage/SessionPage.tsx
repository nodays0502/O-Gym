import axios from 'axios';
import React, { Component } from 'react';
import OpenViduSession from 'openvidu-react';
import jwt_decode from "jwt-decode";
import styled from 'styled-components';

const StyledBackground = styled.div`
    background-image:
    linear-gradient(rgba(0, 0, 255, 0.5), rgba(100, 155, 0, 0.5)),
    url("https://ogymbucket.s3.ap-northeast-2.amazonaws.com/teacher_navbar.jpg");

`;


class SessionPage extends Component {
    OPENVIDU_SERVER_URL: any;
    OPENVIDU_SERVER_SECRET: any;
    state: any;
    constructor(props: any) {
        super(props);
        // this.OPENVIDU_SERVER_URL = 'https://' + window.location.hostname + ':4443';
        this.OPENVIDU_SERVER_URL = 'https://' + 'i5b305.p.ssafy.io' + ':3443';
        this.OPENVIDU_SERVER_SECRET = 'password';
        
        const accessToken = localStorage.getItem('accessToken');
    
        if (accessToken != null) {
        
            const decoded: {
                nickname, role
            } = jwt_decode(accessToken);
        
            this.state = {
                mySessionId: 'SessionA',
                myUserName: decoded['nickname'],
                token: undefined,
            };

        }
        else {

            this.state = {
                mySessionId: 'SessionA',
                myUserName: 'OpenVidu_User_' + Math.floor(Math.random() * 100),
                token: undefined,
            };

            
        }

        this.handlerJoinSessionEvent = this.handlerJoinSessionEvent.bind(this);
        this.handlerLeaveSessionEvent = this.handlerLeaveSessionEvent.bind(this);
        this.handlerErrorEvent = this.handlerErrorEvent.bind(this);
        this.handleChangeSessionId = this.handleChangeSessionId.bind(this);
        this.handleChangeUserName = this.handleChangeUserName.bind(this);
        this.joinSession = this.joinSession.bind(this);
    }

    handlerJoinSessionEvent() {
        console.log('Join session');
    }

    handlerLeaveSessionEvent() {
        console.log('Leave session');
        this.setState({
            session: undefined,
        });
    }

    handlerErrorEvent() {
        console.log('Leave session');
    }

    handleChangeSessionId(e: any) {
        this.setState({
            mySessionId: e.target.value,
        });
    }

    handleChangeUserName(e: any) {
        this.setState({
            myUserName: e.target.value,
        });
    }

    joinSession(event: any) {
        if (this.state.mySessionId && this.state.myUserName) {
            this.getToken().then((token) => {
                this.setState({
                    token: token,
                    session: true,
                });
            });
            event.preventDefault();
        }
    }

    render() {
        const mySessionId = this.state.mySessionId;
        const myUserName = this.state.myUserName;
        const token = this.state.token;
        return (
            <StyledBackground>
                {this.state.session === undefined ? (
                    <div id="join">
                        <div id="join-dialog">
                            <h1> 준비 중입니다.  </h1>
                            <form onSubmit={this.joinSession}>
                                <p>
                                    <label> 이름: </label>
                                    <input
                                        type="text"
                                        id="userName"
                                        value={myUserName}
                                        onChange={this.handleChangeUserName}
                                        required
                                    />
                                </p>
                                <p>
                                    <label> 방 이름: </label>
                                    <input
                                        type="text"
                                        id="sessionId"
                                        value={mySessionId}
                                        onChange={this.handleChangeSessionId}
                                        required
                                    />
                                </p>
                                <p>
                                    <input name="commit" type="submit" value="JOIN" />
                                </p>
                            </form>
                        </div>
                    </div>
                ) : (
                    <div id="session">
                        <OpenViduSession
                            id="opv-session"
                            sessionName={mySessionId}
                            user={myUserName}
                            token={token}
                            joinSession={this.handlerJoinSessionEvent}
                            leaveSession={this.handlerLeaveSessionEvent}
                            error={this.handlerErrorEvent}
                        />
                    </div>
                )}
            </StyledBackground>
        );
    }

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behaviour MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken() {
        return this.createSession(this.state.mySessionId)
            .then((sessionId) => this.createToken(sessionId))
            .catch((Err) => console.error(Err));
    }

    createSession(sessionId: any) {
        return new Promise((resolve, reject) => {
            var data = JSON.stringify({ customSessionId: sessionId });
            axios
                .post(this.OPENVIDU_SERVER_URL + '/openvidu/api/sessions', data, {
                    headers: {
                        Authorization: 'Basic ' + btoa('OPENVIDUAPP:' + this.OPENVIDU_SERVER_SECRET),
                        'Content-Type': 'application/json',
                    },
                })
                .then((response) => {
                    console.log('CREATE SESION', response);
                    resolve(response.data.id);
                })
                .catch((response) => {
                    var error = Object.assign({}, response);
                    if (error.response && error.response.status === 409) {
                        resolve(sessionId);
                    } else {
                        console.log(error);
                        console.warn(
                            'No connection to OpenVidu Server. This may be a certificate error at ' + this.OPENVIDU_SERVER_URL,
                        );
                        if (
                            window.confirm(
                                'No connection to OpenVidu Server. This may be a certificate error at "' +
                                    this.OPENVIDU_SERVER_URL +
                                    '"\n\nClick OK to navigate and accept it. ' +
                                    'If no certificate warning is shown, then check that your OpenVidu Server is up and running at "' +
                                    this.OPENVIDU_SERVER_URL +
                                    '"',
                            )
                        ) {
                            window.location.assign(this.OPENVIDU_SERVER_URL + '/accept-certificate');
                        }
                    }
                });
        });
    }

    createToken(sessionId: any) {
        return new Promise((resolve, reject) => {
            var data = JSON.stringify({});
            axios
                .post(this.OPENVIDU_SERVER_URL + '/openvidu/api/sessions/' + sessionId + '/connection', data, {
                    headers: {
                        Authorization: 'Basic ' + btoa('OPENVIDUAPP:' + this.OPENVIDU_SERVER_SECRET),
                        'Content-Type': 'application/json',
                    },
                })
                .then((response) => {
                    console.log('TOKEN', response);
                    resolve(response.data.token);
                })
                .catch((error) => reject(error));
        });
    }
}


export default SessionPage;
