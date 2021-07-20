import { Modal } from "antd";
import { useRecoilState } from "recoil";
import { ModalState } from "../../recoil/pages/LoginPageState";

const LoginPage = (): JSX.Element => {
    
    const [isModalVisible, setIsModalVisible] = useRecoilState(ModalState);

    const handleOk = () => {
        setIsModalVisible(false);
    }

    const handleCancel = () => {
        setIsModalVisible(false);
    }

    return (
        <>
            <Modal title={'test'}
                visible={isModalVisible}
                onOk={handleOk}
                onCancel={handleCancel}>
                <p>Test.....</p>
            </Modal>
        </>
    );
}

export default LoginPage;