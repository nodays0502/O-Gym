import axios from 'axios';
import React, { Component } from 'react';
import OpenViduSession from 'openvidu-react';
import jwt_decode from "jwt-decode";
import styled from 'styled-components';
// @ts-ignore
import './Session.css';
import Inko from 'inko';
import MainNavigation from '../../components/organisms/Main/Main-Navigation';
import { message, Button, Input, Space } from 'antd';
import { UserOutlined, HomeOutlined  } from '@ant-design/icons';

const StyledBackground = styled.div`
    height: 88vh;
    background-image:
    linear-gradient(rgba(0, 0, 255, 0.5), rgba(100, 155, 0, 0.5)),
    url("https://ogymbucket.s3.ap-northeast-2.amazonaws.com/teacher_navbar.jpg");

`;

const StyledNavigationDiv = styled.div`
height: 12vh;
background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
background-size: 100% 100%;
animation: gradient 15s ease infinite;
`;

const StyledInputDiv = styled.div`
    padding: 30vh 20vh;
`;

const StyledSession = styled(OpenViduSession)`
    #navSessionInfo {
        display: none;
    }
`;

class SessionPage extends Component {
    OPENVIDU_SERVER_URL: any;
    OPENVIDU_SERVER_SECRET: any;
    state: any;
    constructor(props: any) {
        super(props);
        // this.OPENVIDU_SERVER_URL = 'https://' + window.location.hostname + ':4443';
        this.OPENVIDU_SERVER_URL = `https://i5b305.p.ssafy.io:3443`;
        this.OPENVIDU_SERVER_SECRET = `password`;
        this.state = {
            mySessionId: 'SessionA',
            myUserName: 'OpenVidu_User_' + Math.floor(Math.random() * 100),
            token: undefined,
        };
        this.handlerJoinSessionEvent = this.handlerJoinSessionEvent.bind(this);
        this.handlerLeaveSessionEvent = this.handlerLeaveSessionEvent.bind(this);
        this.handlerErrorEvent = this.handlerErrorEvent.bind(this);
        this.handleChangeSessionId = this.handleChangeSessionId.bind(this);
        this.handleChangeUserName = this.handleChangeUserName.bind(this);
        this.joinSession = this.joinSession.bind(this);
    }

    componentDidMount() {
        const accessToken = localStorage.getItem('accessToken');
        message.info({
            content: '????????? ?????? ?????? ???????????? ???????????? ?????? ????????????????????? ??????????????? ??????????????????!',
            className: 'custom-class',
            style: {
              marginTop: '20vh',
            },
          });
        if (accessToken != null) {
        
            const decoded: {
                nickname, role
            } = jwt_decode(accessToken);
        
            if (decoded['nickname']) {
                axios.get(`https://i5b305.p.ssafy.io/api/pt/nowreservation`, {
                    headers: {
                        "Authorization": `Bearer ${accessToken}`
                    }
                }).then(({ data }) => {

                    console.log(data);
                    
                    if (data.data === null) {
                        message.error('?????? ?????? ?????? ????????? ????????????!');
                        throw new Error();
                    }
                        
                    let receivedData: { studentNickname, teacherNickname } = data.data;
                    let inko = new Inko();
                    this.setState({
                        mySessionId: inko.ko2en(receivedData['studentNickname']) + inko.ko2en(receivedData['teacherNickname']),
                        myUserName: decoded['nickname'],
                        token: undefined,
                    });
                    
                }).catch(() => {
                    if (!decoded['nickname']) {
                        message.error('?????? ????????? ????????????!');
                    }
                });

                this.setState({
                    mySessionId: 'SessionA',
                    myUserName: decoded['nickname'],
                    token: undefined,
                });
            }
        
        }
        else {

            this.setState({
                mySessionId: 'SessionA',
                myUserName: 'OpenVidu_User_' + Math.floor(Math.random() * 100),
                token: undefined,
            });
            
        }
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
        return (<>
            {this.state.session !== true ? 
            <StyledNavigationDiv>
                <MainNavigation position="sticky" /> 
                </StyledNavigationDiv> :
                
            <></>
        }
            
            <StyledBackground>
                
                {this.state.session === undefined ? (
                    <StyledInputDiv >
                        <Space direction="vertical">
                                <Input
                                    type="text"
                                    id="userName"
                                    value={myUserName}
                                    onChange={this.handleChangeUserName}
                                    required
                                    size="large" placeholder="please Input UserName" prefix={<UserOutlined />} />
                    
                                <Input type="text"
                                    id="sessionId"
                                    value={mySessionId}
                                    onChange={this.handleChangeSessionId}
                                    required
                                    size="large" placeholder="please Input ClassName" prefix={<HomeOutlined  />} />
                                <Button name="commit" type="primary" onClick={ this.joinSession } block>
                                        JOIN
                                    </Button>

                        </Space>
                    </StyledInputDiv>
                ) : (
                    <div id="session">
                        <StyledSession
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
            </>
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
