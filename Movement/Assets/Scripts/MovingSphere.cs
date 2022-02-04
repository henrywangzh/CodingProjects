using UnityEngine;

public class MovingSphere : MonoBehaviour {
    [SerializeField, Range(0f, 100f)] float maxSpeed = 10f, maxAcceleration = 10f, maxAirAcceleration = 5f;
    // [SerializeField] Rect allowedArea = new Rect(-5f, -5f, 10f, 10f);
    // [SerializeField, Range(0f, 1f)] float bounciness = 0.5f;
    [SerializeField, Range(0f, 10f)] float jumpHeight = 2f;
    [SerializeField, Range(0, 5)] int maxAirJumps = 1;
    [SerializeField, Range(0f, 90f)] float maxGroundAngle = 25f;
    int jumpPhase;
    Vector3 velocity, desiredVelocity;
    Rigidbody body;
    bool desiredJump, onGround;
    float minGroundDotProduct;
    Vector3 contactNormal;

    private void OnCollisionEnter(Collision collision) {
        EvaluateCollision(collision);
    }
    private void OnCollisionStay(Collision collision) {
        EvaluateCollision(collision);
    }
    void OnValidate() {
        minGroundDotProduct = Mathf.Cos(maxGroundAngle * Mathf.Deg2Rad);
    }
    Vector3 ProjectOnContactPlane(Vector3 vector) {
        return vector - contactNormal * Vector3.Dot(vector, contactNormal);
    }
    void AdjustVelocity() {
        Vector3 xAxis = ProjectOnContactPlane(Vector3.right).normalized;
        Vector3 zAxis = ProjectOnContactPlane(Vector3.forward).normalized;
        float currentX = Vector3.Dot(velocity, xAxis);
        float currentZ = Vector3.Dot(velocity, zAxis);
        float acceleration = onGround ? maxAcceleration : maxAirAcceleration;
        float maxSpeedChange = acceleration * Time.deltaTime;

        float newX =
            Mathf.MoveTowards(currentX, desiredVelocity.x, maxSpeedChange);
        float newZ =
            Mathf.MoveTowards(currentZ, desiredVelocity.z, maxSpeedChange);
        velocity += xAxis * (newX - currentX) + zAxis * (newZ - currentZ);
    }

    private void EvaluateCollision(Collision collision) {
        for (int i = 0; i < collision.contactCount; i++) {
            Vector3 normal = collision.GetContact(i).normal;
            //onGround |= normal.y >= minGroundDotProduct;
            if (normal.y >= minGroundDotProduct) {
                onGround = true;
                contactNormal = normal;
            }
        }
    }
    private void Awake() {
        body = GetComponent<Rigidbody>();
        OnValidate();
    }

    private void Update() {
        Vector2 playerInput;
        playerInput.x = Input.GetAxis("Horizontal");
        playerInput.y = Input.GetAxis("Vertical");
        playerInput = Vector2.ClampMagnitude(playerInput, 1f);

        //Vector3 acceleration = new Vector3(playerInput.x, 0f, playerInput.y) * maxSpeed;
        desiredVelocity = new Vector3(playerInput.x, 0f, playerInput.y) * maxSpeed;

        desiredJump |= Input.GetButtonDown("Jump");
    }
    void FixedUpdate () {
        UpdateState();
        AdjustVelocity();
      //  float acceleration = onGround ? maxAcceleration : maxAirAcceleration; 
       //     float maxSpeedChange = acceleration * Time.deltaTime;
            //     if (velocity.x < desiredVelocity.x) {
            //         velocity.x = Mathf.Min(velocity.x + maxSpeedChange, desiredVelocity.x);
            //     }
            //    else if (velocity.x > desiredVelocity.x) {
            //         velocity.x = Mathf.Max(velocity.x - maxSpeedChange, desiredVelocity.x);
            //      }
     //       velocity.x = Mathf.MoveTowards(velocity.x, desiredVelocity.x, maxSpeedChange);
      //      velocity.z = Mathf.MoveTowards(velocity.z, desiredVelocity.z, maxSpeedChange);
        //Vector3 displacement = velocity * Time.deltaTime;
        // Vector3 newPosition = transform.localPosition + displacement;
        //if (!allowedArea.Contains(new Vector2(newPosition.x, newPosition.z))) {
        // newPosition = transform.localPosition;
        //    newPosition.x = Mathf.Clamp(newPosition.x, allowedArea.xMin, allowedArea.xMax);
        //    newPosition.z = Mathf.Clamp(newPosition.z, allowedArea.yMin, allowedArea.yMax);
        // }
        // if (newPosition.x < allowedArea.xMin) {
        //      newPosition.x = allowedArea.xMin;
        //       velocity.x *= -1 * bounciness;
        //   } else if (newPosition.x > allowedArea.xMax) {
        //       newPosition.x = allowedArea.xMax;
        //        velocity.x *= -1 * bounciness;
        //    }
        //    if (newPosition.z < allowedArea.yMin) {
        //       newPosition.z = allowedArea.yMin;
        //        velocity.z *= -1 * bounciness;
        //   } else if (newPosition.z > allowedArea.yMax) {
        //       newPosition.z = allowedArea.yMax;
        //       velocity.z *= -1 * bounciness;
        //    }
        //  transform.localPosition = newPosition;
        if(desiredJump) {
            desiredJump = false;
            Jump();
        }
        body.velocity = velocity;
        onGround = false;
            
        }
       void Jump() {
        if(onGround || jumpPhase < maxAirJumps) {
            jumpPhase += 1;
            float jumpSpeed = Mathf.Sqrt(-2f * Physics.gravity.y * jumpHeight);
            float alignedSpeed = Vector3.Dot(velocity, contactNormal);
            if (alignedSpeed > 0f) {
                jumpSpeed = Mathf.Max(jumpSpeed - alignedSpeed, 0f);
            }
            if (alignedSpeed < 0f) {
                jumpSpeed -= alignedSpeed;
            }
            velocity += contactNormal * jumpSpeed; ;
        }
       
    }
    void UpdateState() {
        velocity = body.velocity;
        if (onGround) {
            jumpPhase = 0;
        }
        else {
            contactNormal = Vector3.up;
        }
    }
    
}