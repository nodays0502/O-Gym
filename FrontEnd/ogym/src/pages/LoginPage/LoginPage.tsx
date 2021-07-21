import { Modal, Tabs } from "antd";
import { useRecoilState } from "recoil";
import styled from "styled-components";
import { ModalState } from "../../recoil/pages/LoginPageState";
import Register from "../../components/organisms/Register";

const { TabPane } = Tabs;

// const LoginTag = () => {
//     return (
//         <div className={styles.loginwidth}>

//         </div>
//     );
// }

const StyledTabs = styled(Tabs)`
    width: 100%;

    .ant-tabs-tab, .ant-tabs-nav-list {
        width: 100%;
    }
    
`;

const StyledTabPane = styled(TabPane)`
    width: 100%;
`;

const LoginPage = (): JSX.Element => {
    
    // const [isModalVisible, setIsModalVisible] = useRecoilState(ModalState);

    // const handleOk = () => {
    //     setIsModalVisible(false);
    // }

    // const handleCancel = () => {
    //     setIsModalVisible(false);
    // }

    return (
        <>
            <Modal title={'test'}
                visible={true}
                // onOk={handleOk}
                // onCancel={handleCancel}
            >
                <StyledTabs  type="card">
                    <StyledTabPane tab="LOGIN" key="1">
                        <p>test1</p>
                    </StyledTabPane>
                    
                    <StyledTabPane tab="REGISTER" key="2">
                        <Register />
                    </StyledTabPane>

                </StyledTabs>
            </Modal>
        </>
    );
}

export default LoginPage;